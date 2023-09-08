package cgg.a06;

import cgg.a05.Hit;
import cgg.a05.Material;
import cgg.a05.Ray;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public record GlassSurface(Color color) implements Material {
    // Luft
    private static double n1 = 1;
    // Glas
    private static double n2 = 1.5;
    // Wasser
    // private static double nWater = 1.3;

    public Ray secondaryRay(Ray ray, Hit hit) {
        double swapn1 = n1;
        double swapn2 = n2;
        Direction negateNormalDirection = hit.normalVector();
        if (Vector.dotProduct(ray.directionPoint(), hit.normalVector()) > 0) {
            swapn1 = n2;
            swapn2 = 1;
            negateNormalDirection = Vector.negate(hit.normalVector());
        }

        if (refract(ray.directionPoint(), hit.normalVector(), swapn1, swapn2) != null) {
            if (Random.random() > schlick(ray.directionPoint(), negateNormalDirection, swapn1, swapn2)) {
                return new Ray(ray.pointAt(hit.t()),
                        refract(ray.directionPoint(), negateNormalDirection, swapn1, swapn2), 0.0001,
                        Double.POSITIVE_INFINITY);
            }
            return new Ray(ray.pointAt(hit.t()), reflect(ray.directionPoint(), negateNormalDirection), 0.0001,
                    Double.POSITIVE_INFINITY);
        }
        return new Ray(ray.origin(), reflect(ray.directionPoint(), hit.normalVector()), 0.0001,
                Double.POSITIVE_INFINITY);
    }

    public Color albedo(Ray r, Hit h) {
        return color;
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