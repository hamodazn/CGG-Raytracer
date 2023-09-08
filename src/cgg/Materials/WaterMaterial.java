package cgg.Materials;

import cgg.BaseTools.ConstantColor;
import cgg.BaseTools.Hit;
import cgg.BaseTools.Material;
import cgg.BaseTools.Ray;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Sampler;
import cgtools.Vector;

public class WaterMaterial implements Material {

    protected Sampler albedo;
    protected double murk;
    // Luft
    private static double n1 = 1;
    // Wasser
    private static double n2 = 1.3;

    public WaterMaterial(Color color, double murk) {
        this.albedo = new ConstantColor(color);
        this.murk = murk;
    }

    public WaterMaterial(Sampler texture, double murk) {
        this.albedo = texture;
        this.murk = murk;
    }

    public Ray getSecondaryRay(Ray ray, Hit hit) {

        if (Random.random() > murk) {
            return new Ray(hit.point(), ray.direction(), 0.0001, ray.tMax());
        }

        Direction d = ray.direction();
        Direction n = hit.normalVector();

        if (Vector.dotProduct(d, n) > murk) {
            n = Vector.negate(n);
            double assistN = n1;
            n1 = n2;
            n2 = assistN;
        }
        Direction r = refract(d, n, n1, n2);
        if(r!= null){
            if(Random.random()> schlick(d, n, n1, n2)){
                return new Ray(hit.point(), r, 0.00001, ray.tMax());
            }
        }

        return new Ray(hit.point(), reflect(d, n), 0.000001, ray.tMax());
    }

    public Color albedo(Ray r, Hit h) {
        return albedo.getColor(h.u(), h.v());
    }

    public Color emission(Ray r, Hit h) {
        return Vector.black;
    }

    public Direction refract(Direction rayDirection, Direction normalDirection, double n1, double n2) {
        // Vektorielle Hilfsgrößen
        double r = n1 / n2;
        double c = Vector.dotProduct(Vector.negate(normalDirection), rayDirection);
        double discriminant = 1 - ((r * r) * (1 - (c * c)));

        if (discriminant >= 0) {
            // Hilfsvariablen für die Übersicht
            Direction rAndDirection = Vector.multiply(r, rayDirection);
            Direction restDirection = Vector.multiply(r * c - Math.sqrt(discriminant), normalDirection);

            // Snelliusches Brechungsgesetzt - Strahlrichtung
            Direction snellDirection = Vector.add(rAndDirection, restDirection);
            return snellDirection;
        }
        return null;
    }

    public double schlick(Direction rayDirection, Direction normalDirection, double n1, double n2) {
        // Hilfsvariablen
        double r0 = ((n1 - n2) / (n1 + n2)) * ((n1 - n2) / (n1 + n2));
        double dotProductTerm = Math.pow(1 + Vector.dotProduct(normalDirection, rayDirection), 5);
        double reflexionFactor = r0 + ((1 - r0) * dotProductTerm);
        return reflexionFactor;
    }

    public Direction reflect(Direction rayDirection, Direction normalDirection) {
        Direction b = Vector.multiply(Vector.dotProduct(Vector.negate(rayDirection), normalDirection),
                normalDirection);
        Direction r = Vector.add(b, b, rayDirection);
        return r;
    }

}