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

public class GlassMaterial implements Material {

    protected Sampler albedo;
    // Luft
    private static double n1 = 1;
    // Glas
    private static double n2 = 1.5;
    // Wasser
    // private static double nWater = 1.3;

    public GlassMaterial(Color color) {
        this.albedo = new ConstantColor(color);
    }

    public GlassMaterial(Sampler texture) {
        this.albedo = texture;
    }

    public Ray getSecondaryRay(Ray ray, Hit hit) {
        double swapn1 = n1;
        double swapn2 = n2;
        Direction negateNormalDirection = hit.normalVector();
        if (Vector.dotProduct(ray.direction(), hit.normalVector()) > 0) {
            swapn1 = n2;
            swapn2 = 1;
            negateNormalDirection = Vector.negate(hit.normalVector());
        }

        if (refract(ray.direction(), hit.normalVector(), swapn1, swapn2) != null) {
            if (Random.random() > schlick(ray.direction(), negateNormalDirection, swapn1, swapn2)) {
                return new Ray(ray.pointAt(hit.t()),
                        refract(ray.direction(), negateNormalDirection, swapn1, swapn2), 0.0001,
                        Double.POSITIVE_INFINITY);
            }
            return new Ray(ray.pointAt(hit.t()), reflect(ray.direction(), negateNormalDirection), 0.0001,
                    Double.POSITIVE_INFINITY);
        }
        return new Ray(ray.origin(), reflect(ray.direction(), hit.normalVector()), 0.0001,
                Double.POSITIVE_INFINITY);
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