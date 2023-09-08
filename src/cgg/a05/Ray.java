package cgg.a05;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Ray(Point origin, Direction directionPoint, double tMin, double tMax) {

    public Point pointAt(double t) {
        return Vector.add(Vector.multiply(t, directionPoint), origin);
    }
    public boolean isValid(double t) {
        if (tMin <= t && t <= tMax) {
            return true;
        }
        return false;
    }
}