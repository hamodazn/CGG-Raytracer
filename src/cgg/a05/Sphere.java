package cgg.a05;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Sphere(double radius, Material material, Point centerPoint) implements Shape {

    public Hit intersect(Ray r) {
        Direction x0 = Vector.subtract(r.origin(), centerPoint);
        double a = Vector.dotProduct(r.directionPoint(), r.directionPoint());
        double b = 2 * Vector.dotProduct(r.directionPoint(), x0);
        double c = Vector.dotProduct(x0, x0) - radius * radius;
        double dis1 = b * b - 4 * a * c;

        if (dis1 >= 0) {
            double t0 = (-b - Math.sqrt(dis1)) / 2 * a;

            if (r.isValid(t0)) {
                Direction nVector0 = Vector.divide(Vector.subtract(r.pointAt(t0), centerPoint), radius);
                return new Hit(t0, r.pointAt(t0), Vector.normalize(nVector0), material);

            }
        }
        return null;
    }
}