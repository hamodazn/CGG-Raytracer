package cgg.Shapes;

import cgg.BaseTools.Hit;
import cgg.BaseTools.Material;
import cgg.BaseTools.Ray;
import cgg.BaseTools.Shape;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Sphere(double radius, Material material, Point centerPoint) implements Shape {

    public Hit intersect(Ray r) {
        Direction x0 = Vector.subtract(r.origin(), centerPoint);
        double a = r.direction().x() * r.direction().x() + r.direction().y() * r.direction().y()
                + r.direction().z() * r.direction().z();
        double b = 2 * r.direction().x() * x0.x() + 2 * r.direction().y() * x0.y() + 2 * r.direction().z() * x0.z();
        double c = (x0.x() * x0.x() + x0.y() * x0.y() + x0.z() * x0.z()) - (radius * radius);
        double dis1 = b * b - 4 * a * c;

        if (dis1 >= 0) {
            double t0 = (-b - Math.sqrt(dis1)) / (2 * a);
            double t1 = (-b + Math.sqrt(dis1)) / (2 * a);

            if (r.isValid(t0)) {
                Direction nVector0 = Vector.divide(Vector.subtract(r.pointAt(t0), centerPoint), radius); 
                double inc = Math.acos(nVector0.y());
                double azi = Math.PI + Math.atan2(nVector0.x(), nVector0.z());
                double u = azi / (2 * Math.PI);
                double v = inc / Math.PI;
                return new Hit(t0, r.pointAt(t0), nVector0, material, u, v);
            } else if (r.isValid(t1)) {
                Direction nVector1 = Vector.divide(Vector.subtract(r.pointAt(t1), centerPoint), radius);
                double inc = Math.acos(nVector1.y());
                double azi = Math.PI + Math.atan2(nVector1.x(), nVector1.z());
                double u = azi / (2 * Math.PI);
                double v = inc / Math.PI;
                return new Hit(t1, r.pointAt(t1), nVector1, material, u, v);
            }
        }
        return null;
    }
}