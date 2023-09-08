package cgg.Shapes;

import cgg.BaseTools.Hit;
import cgg.BaseTools.Material;
import cgg.BaseTools.Ray;
import cgg.BaseTools.Shape;
import cgtools.*;

public class CylinderLateral implements Shape {

    protected Material material;
    protected Point centerPoint;
    protected double radius;
    protected double height;

    public CylinderLateral(Material material, Point centerPoint, double radius, double height) {
        this.material = material;
        this.centerPoint = centerPoint;
        this.radius = radius;
        this.height = height;
    }

    public Hit intersect(Ray r) {

        Direction cylinderDirection = new Direction(centerPoint.x(), centerPoint.y(), centerPoint.z());
        Point newPoint = Vector.subtract(r.origin(), cylinderDirection);
        Direction newDirection = new Direction(r.direction().x(), 0,
                r.direction().z());
        newPoint = new Point(newPoint.x(), newPoint.y() * 0, newPoint.z());

        double a = Vector.dotProduct(newDirection, newDirection);
        double b = 2 * Vector.dotProduct(newPoint, newDirection);
        double c = Vector.dotProduct(newPoint, newPoint) - (radius * radius);

        double dis = (b * b) - (4 * a * c);

        double t0 = (-b - Math.sqrt(dis)) / (2 * a);
        double t1 = (-b + Math.sqrt(dis)) / (2 * a);

        Point pointy0 = r.pointAt(t0);
        Point pointy1 = r.pointAt(t1);

        double y0 = pointy0.y();
        double y1 = pointy1.y();

        if (dis >= 0) {
            if (r.isValid(t0)) {
                if (y0 > centerPoint.y() + height || y0 < centerPoint.y()) {
                    return null;
                }
                Direction normalDirection = Vector
                        .normalize(Vector.divide(Vector.subtract(pointy0, centerPoint), radius));
                double inc = Math.cos(normalDirection.y());
                double azi = Math.PI + Math.atan2(normalDirection.x(), normalDirection.z());
                double u = azi / (2 * Math.PI);
                double v = inc / Math.PI;
                return new Hit(t0, pointy0, normalDirection, material, u, v);

            } else if (r.isValid(t1)) {
                if (y1 > centerPoint.y() + height || y1 < centerPoint.y()) {
                    return null;
                }
                Direction normalDirection = Vector
                        .normalize(Vector.divide(Vector.subtract(pointy1, centerPoint), radius));
                double inc = Math.cos(normalDirection.y());
                double azi = Math.PI + Math.atan2(normalDirection.x(), normalDirection.z());
                double u = azi / (2 * Math.PI);
                double v = inc / Math.PI;
                return new Hit(t1, pointy1, normalDirection, material, u, v);
            }
        }

        return null;
    }

    public Material material() {
        return material;
    }

    public Point centerPoint() {
        return centerPoint;
    }

    public double radius() {
        return radius;
    }

    public double height(){
        return height;
    }
}
