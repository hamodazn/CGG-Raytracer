package cgg.a08;


import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public record Glass(Color color) implements Material {
    private static double n1 = 1;
    private static double n2 = 1.5;


    public Ray scatteredRay(Ray ray, Hit hit) {
        double swapn1 = n1;
        double swapn2 = n2;
        Direction negateNormalDirection = hit.normalVector();
        if (Vector.dotProduct(ray.direction, hit.normalVector()) > 0) {
            swapn1 = n2;
            swapn2 = 1;
            negateNormalDirection = Vector.negate(hit.normalVector());
        }

        if (refract(ray.direction, hit.normalVector(), swapn1, swapn2) != null) {
            if (Random.random() > schlick(ray.direction, negateNormalDirection, swapn1, swapn2)) {
                return new Ray(ray.pointAt(hit.t()),
                        refract(ray.direction, negateNormalDirection, swapn1, swapn2), 0.0001,
                        Double.POSITIVE_INFINITY);
            }
            return new Ray(ray.pointAt(hit.t()), reflect(ray.direction, negateNormalDirection), 0.0001,
                    Double.POSITIVE_INFINITY);
        }
        return new Ray(ray.origin, reflect(ray.direction, hit.normalVector()), 0.0001,
                Double.POSITIVE_INFINITY);
    }

    public Color albedo(Ray ray, Hit hit) {
        return color;
    }

    public Color emission(Ray ray, Hit hit) {

        return Vector.black;
    }

    public Direction refract(Direction rayDir, Direction normalDir, double n1, double n2) {
        double r = n1 / n2;
        double c = Vector.dotProduct(Vector.negate(normalDir), rayDir);
        double discriminant = 1 - ((r * r) * (1 - (c * c)));

        if (discriminant >= 0) {
            Direction rAndDirection = Vector.multiply(r, rayDir);
            Direction restDirection = Vector.multiply(r * c - Math.sqrt(discriminant), normalDir);

            Direction snellDirection = Vector.add(rAndDirection, restDirection);
            return snellDirection;
        }
        return null;
    }

    public double schlick(Direction rayDir, Direction normalDir, double n1, double n2) {
        double r0 = ((n1 - n2) / (n1 + n2)) * ((n1 - n2) / (n1 + n2));
        double dotProductTerm = Math.pow(1 + Vector.dotProduct(normalDir, rayDir), 5);
        double reflexionFactor = r0 + ((1 - r0) * dotProductTerm);
        return reflexionFactor;
    }

    public Direction reflect(Direction rayDir, Direction normalDir) {
        Direction b = Vector.multiply(Vector.dotProduct(Vector.negate(rayDir), normalDir),
                normalDir);
        Direction r = Vector.add(b, b, rayDir);
        return r;
    }
}