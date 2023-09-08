package cgg.a04;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Sphere(double radius, Color color, Point centerPoint) implements Shape {

    public Hit intersect(Ray r) {
        Direction x0 = Vector.subtract(r.origin(), centerPoint);    
        double a = Vector.dotProduct(r.directionPoint(), r.directionPoint());
        double b = 2 * Vector.dotProduct(r.directionPoint(), x0);
        double c = Vector.dotProduct(x0, x0) - radius * radius;
        double dis = b * b - 4 * a * c;

        if (dis >= 0) {
            double smallT = (-b - Math.sqrt(dis)) / 2 * a;
            if (r.isValid(smallT)) {
                Direction nVector = Vector.divide(Vector.subtract(r.pointAt(smallT), centerPoint), radius);
                return new Hit(smallT, r.pointAt(smallT), Vector.normalize(nVector), color);
            }
        }
        return null;
    }
}