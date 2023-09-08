package cgg.Playground;

import cgg.a05.Hit;
import cgg.a05.Material;
import cgg.a05.Ray;
import cgg.a05.Shape;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Halfsphere(double radius, Material material1, Material material2, Point centerPoint) implements Shape {

    public Hit intersect(Ray r) {
        Direction x0 = Vector.subtract(r.origin(), centerPoint);
        double a = r.directionPoint().x()*r.directionPoint().x() + r.directionPoint().y()*r.directionPoint().y() + r.directionPoint().z()*r.directionPoint().z();
        double b = 2 * r.directionPoint().x()*x0.x() + 2 * r.directionPoint().y()*x0.y() + 2 * r.directionPoint().z() * x0.z();
        double c = (x0.x()*x0.x() + x0.y()*x0.y() + x0.z()*x0.z()) - (radius * radius);
        double dis1 = b * b - 4 * a * c;

        if (dis1 >= 0) {
            double t0 = (-b - Math.sqrt(dis1)) / (2 * a);
            double t1 = (-b + Math.sqrt(dis1)) / (2 * a);
            if (r.isValid(t0)) {
                Direction nVector0 = Vector.divide(Vector.subtract(r.pointAt(t0), centerPoint), radius);
                if(r.pointAt(t0).y() > 0){
                    return new Hit(t0, r.pointAt(t0), Vector.normalize(nVector0), material1);
                }
                return new Hit(t0,r.pointAt(t0), nVector0, material2);
            } else if (r.isValid(t1)) {
                Direction nVector1 = Vector.divide(Vector.subtract(r.pointAt(t1), centerPoint), radius);
                if(r.pointAt(t1).y() > 0){
                    return new Hit(t1, r.pointAt(t1), Vector.normalize(nVector1), material1);
                }
                return new Hit(t1, r.pointAt(t1), nVector1, material2);
            }
        }
        return null;
    }
}