package cgg.a08;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Plane(Material material, Point point, Direction direction, double r) implements Shape {

    public Hit intersect(Ray ray) {

        double t = Vector.dotProduct(Vector.subtract(point, ray.origin), direction)/Vector.dotProduct(ray.direction, direction);
        
        if(Vector.length(Vector.subtract(ray.pointAt(t), point)) > r) {
            return null;
        } else if(ray.isValid(t)){
            return new Hit(t, ray.pointAt(t), direction, material);
        } return null;
    }   
}
