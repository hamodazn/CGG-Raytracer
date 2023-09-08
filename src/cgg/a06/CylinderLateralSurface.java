package cgg.a06;

import cgg.a05.Hit;
import cgg.a05.Material;
import cgg.a05.Ray;
import cgg.a05.Shape;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

//Quelle: https://www.cl.cam.ac.uk/teaching/1999/AGraphHCI/SMAG/node2.html

public record CylinderLateralSurface(Material material, Point centerPoint, double radius, double height)
        implements Shape {

    public Hit intersect(Ray r) {

        Direction cylinderDirection = new Direction(centerPoint.x(), centerPoint.y(), centerPoint.z());
        Point newPoint = Vector.subtract(r.origin(), cylinderDirection);
        Direction newDirection = new Direction(r.directionPoint().x(), 0,
                r.directionPoint().z());
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
                return new Hit(t0, pointy0, normalDirection, material);

            } else if (r.isValid(t1)) {
                if (y1 > centerPoint.y() + height || y1 < centerPoint.y()) {
                    return null;
                }
                Direction normalDirection = Vector
                        .normalize(Vector.divide(Vector.subtract(pointy1, centerPoint), radius));
                return new Hit(t1, pointy1, normalDirection, material);
            }
        }

        return null;
    }
}
