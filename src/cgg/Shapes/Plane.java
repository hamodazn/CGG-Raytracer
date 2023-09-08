package cgg.Shapes;

import cgg.BaseTools.Hit;
import cgg.BaseTools.Material;
import cgg.BaseTools.Ray;
import cgg.BaseTools.Shape;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Plane(Point anchorPoint, Direction normalVector, double radius, Material material) implements Shape {

    public Hit intersect(Ray r) {
        double t = Vector.dotProduct(Vector.subtract(anchorPoint, r.origin()), normalVector)
                / Vector.dotProduct(r.direction(), normalVector);

        if (Vector.length(Vector.subtract(r.pointAt(t), anchorPoint)) > radius) {
            return null;
        } else if (r.isValid(t)) {
            double u = r.pointAt(t).x() / (2 * radius) + 0.5;
            double v = r.pointAt(t).z() / (2 * radius) + 0.5;
            return new Hit(t, r.pointAt(t), normalVector, material, u, v);
        }
        return null;

    }
}
