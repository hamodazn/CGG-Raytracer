package cgg.a04;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;
import cgtools.Vector;

public record Background(Color color) implements Shape{

    public Hit intersect(Ray r) {
        double tInfinity = Double.POSITIVE_INFINITY;
        if(r.tMax() == tInfinity) {
            return new Hit(tInfinity, r.pointAt(tInfinity), Vector.negate(r.directionPoint()), color);
        } else {
            return null;
        }
    }
}