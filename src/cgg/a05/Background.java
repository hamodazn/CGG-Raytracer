package cgg.a05;

import cgtools.Vector;

public record Background(Material material) implements Shape{

    public Hit intersect(Ray r) {
        double tInfinity = Double.POSITIVE_INFINITY;
        if(r.tMax() == tInfinity) {
            return new Hit(tInfinity, r.pointAt(tInfinity), Vector.negate(r.directionPoint()), material);
        } else {
            return null;
        }
    }
}