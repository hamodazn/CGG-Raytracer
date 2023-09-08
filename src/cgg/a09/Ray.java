package cgg.a09;

import cgtools.*;


public record Ray( Direction dirVector, Point origin, double tmax, double tmin) {
    

    public Point pointAt(double t){
        Point point = Vector.add(origin, Vector.multiply(dirVector, t));
        return point;
    }

    public boolean isValid(double t){
        return  t <= tmax && t >= tmin;
    }

    @Override
    public String toString() {
        return dirVector.toString();
    }


}
