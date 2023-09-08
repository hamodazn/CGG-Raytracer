package cgg.a08;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Ray {
    Point origin; 
    Direction direction;
    double tMin;
    double tMax;

    public Ray(Point origin, Direction direction, double tMin, double tMax){
        this.origin = origin;
        this.direction  = direction;
        this.tMin = tMin;
        this.tMax = tMax;
    }

    public Point pointAt(double t) {
        return Vector.add(Vector.multiply(t, direction), origin);
    }
    public boolean isValid(double t) {
        if (tMin <= t && t <= tMax) {
            return true;
        }
        return false;
    }
}