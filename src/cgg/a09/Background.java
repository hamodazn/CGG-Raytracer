package cgg.a09;

import cgtools.*;

public record Background(Material material) implements Shape {

    public Hit intersect(Ray r){
        if(Double.POSITIVE_INFINITY == r.tmax()){
            double inf = Double.MAX_VALUE;
            Point hit = r.pointAt(Double.MAX_VALUE);
            Direction normal = Vector.divide(Vector.subtract(hit, new Point(0, 0, 0)), inf);
            double inclination = Math.acos(normal.y());
            double azimuth = Math.PI + Math.atan2(normal.x(), normal.z());
            double u = azimuth / (2 * Math.PI);
            double v = inclination / Math.PI;
            return new Hit(hit, u , v, inf , material, normal );
        }
        return null;
    }
}