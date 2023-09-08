package cgg.a09;


import cgtools.*;
import static cgtools.Vector.*;
import static cgg.a09.Main.*;

public record Plane(Point p, Direction normalVector, Material material, double r) implements Shape{


    public Hit intersect(Ray ray){
        
        double a = Vector.dotProduct(Vector.subtract(p, ray.origin()), Vector.normalize(normalVector));
        double b = Vector.dotProduct(ray.dirVector(), Vector.normalize(normalVector));
        if(b == 0){
            return null;
        }
        double t = a / b;
        if(ray.isValid(t)){
            Point hit = ray.pointAt(t);
            if(Vector.length(Vector.subtract(hit, p)) > r){
                return null;
            }
      
            /* return new Hit(hit,  hit.x() / WIDTH + 0.5 , hit.z() / r + 0.5 , t, material, normalVector); */
            return new Hit(hit, hit.x() - Math.floor(hit.x()), hit.z() - Math.floor(hit.z()), t,  material, normalVector);
            /* return new Hit(hit, hit.x() - Math.floor(hit.x()), hit.z() - Math.floor(hit.z()), t,  material, normalVector); */
        }
        return null;
    }
}

