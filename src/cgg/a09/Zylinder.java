package cgg.a09;
import cgtools.*;
import static cgtools.Vector.*;

import java.util.ArrayList;



public record Zylinder(Point center, double height, double radius, Material material) implements Shape {

     public Hit intersect(Ray r) { 
        Direction x0 = subtract(r.origin(), center);
        double a = Math.pow(r.dirVector().x(), 2) + Math.pow( r.dirVector().z(), 2);
        double b =  2 * x0.x() * r.dirVector().x() 
                +   2 * x0.z() * r.dirVector().z();               
        double c =  x0.x() * x0.x()
                +   x0.z() * x0.z() 
                -   radius * radius ;

        double discriminant = b * b - 4 * a * c;
        if(discriminant < 0){
            return null;
        }
        double t0 = (-b - Math.sqrt(discriminant)) / (2 * a); 
        double t1 = (-b + Math.sqrt(discriminant)) / (2 * a); 

        if(t1 < t0){
            if(r.isValid(t1)){
                Point hit = r.pointAt(t1);
                if(hit.y() > center.y() && hit.y() < center.y() + height){
                    Point np = new Point(hit.x() - center.x(), 0, hit.z() - center.z());
                    Direction n = Vector.divide(np, radius);
                    double u = 0;
                    double v = 0;
                    return new Hit(hit, t0, u, v, material, n);
                }
            }  
        }
        else{
           if(r.isValid(t0)){
                Point hit = r.pointAt(t0);
                if(hit.y() > center.y() && hit.y() < center.y() + height){
                    Point np = new Point(hit.x() - center.x(), 0, hit.z() - center.z());
                    Direction n = Vector.divide(np, radius); 
                    double u = 0;
                    double v = 0;
                    return new Hit(hit, t0, u, v, material, n);
                }
            }
        }        
        return null;
    } 


    public static Group cappedZylinder(Point center, double height, double radius, Material material){
        ArrayList<Shape> zylinder = new ArrayList<Shape>();
        Point topCenter = new Point(center.x(), center.y() + height, center.z());
        // DiffMaterial s = new DiffMaterial(Vector.red);
       
        Plane top = new Plane(topCenter, new Direction(0,1,0), material, radius);
        Plane bottom = new Plane(center, new Direction(0,-1,0), material, radius);

        zylinder.add(new Zylinder(center, height, radius, material));

        zylinder.add(top);
        zylinder.add(bottom);
        return new Group(zylinder, new Transformation(Matrix.identity()));
    }
}

