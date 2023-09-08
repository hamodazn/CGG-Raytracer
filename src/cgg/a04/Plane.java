package cgg.a04;

import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Plane(Color planeColor, Point anchorPoint, Direction nPlaneVector, double r) implements Shape {

    public Hit intersect(Ray ray) {

        double t = Vector.dotProduct(Vector.subtract(anchorPoint, ray.origin()), nPlaneVector)/Vector.dotProduct(ray.directionPoint(), nPlaneVector);
        
        if(Vector.length(Vector.subtract(ray.pointAt(t), anchorPoint)) > r) {
            return null;
        } else if(ray.isValid(t)){
            return new Hit(t, ray.pointAt(t), nPlaneVector, planeColor);
        } return null;

        // if(ray.isValid(t)){
        //     return new Hit(t, ray.pointAt(t), nPlaneVector, planeColor);
        // } return null;
    }   
}
