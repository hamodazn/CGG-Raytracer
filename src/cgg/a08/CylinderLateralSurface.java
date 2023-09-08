package cgg.a08;


import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

//Quelle: https://www.cl.cam.ac.uk/teaching/1999/AGraphHCI/SMAG/node2.html

public record CylinderLateralSurface(Material material, Point center, double radius, double height)
        implements Shape {

    public Hit intersect(Ray r) {

        Direction cylinderDir = new Direction(center.x(), center.y(), center.z());
        Point newPoint = Vector.subtract(r.origin, cylinderDir);
        Direction newDir = new Direction(r.direction.x(), 0,
                r.direction.z());
        newPoint = new Point(newPoint.x(), newPoint.y() * 0, newPoint.z());

        double a = Vector.dotProduct(newDir, newDir);
        double b = 2 * Vector.dotProduct(newPoint, newDir);
        double c = Vector.dotProduct(newPoint, newPoint) - (radius * radius);

        double dis = (b * b) - (4 * a * c);

        double t0 = (-b - Math.sqrt(dis)) / (2 * a);
        double t1 = (-b + Math.sqrt(dis)) / (2 * a);

        Point py0 = r.pointAt(t0);
        Point py1 = r.pointAt(t1);

        double y0 = py0.y();
        double y1 = py1.y();

        if (dis >= 0) {
            if (r.isValid(t0)) {
                if (y0 > center.y() + height || y0 < center.y()) {
                    return null;
                }
                Direction normalDir = Vector
                        .normalize(Vector.divide(Vector.subtract(py0, center), radius));
                return new Hit(t0, py0, normalDir, material);

            } else if (r.isValid(t1)) {
                if (y1 > center.y() + height || y1 < center.y()) {
                    return null;
                }
                Direction normalDir = Vector
                        .normalize(Vector.divide(Vector.subtract(py1, center), radius));
                return new Hit(t1, py1, normalDir, material);
            }
        }

        return null;
    }
}
