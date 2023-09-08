package cgg.a09;
import static cgg.a09.Main.*;
import cgtools.*;

public class Sphere implements Shape{
protected Point center;
protected double radius;
protected Material material;


    public Sphere(Point center, double radius, Material material){
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public Hit intersect(Ray r){
        Direction x0 = Vector.subtract(r.origin(), center);
        double a = Vector.squaredLength(r.dirVector());
        double b = Vector.dotProduct(Vector.multiply(x0, 2), r.dirVector());
        double c = Vector.squaredLength(x0) - radius * radius;
        double t0 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
        double t1 = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);

        if( r.isValid(t0)){
            Point hit = r.pointAt(t0);
            Direction normal = Vector.divide(Vector.subtract(hit, center), radius);
            double inclination = Math.acos(normal.y());
            double azimuth = Math.PI + Math.atan2(normal.x(), normal.z());
            double u = azimuth / (2 * Math.PI);
            double v = inclination / Math.PI;
             return new Hit(hit, u , v, t0, material, normal);
        }
        else if(r.isValid(t1)){
            Point hit = r.pointAt(t1);
            Direction normal = Vector.divide(Vector.subtract(hit, center), radius);
            double inclination = Math.acos(normal.y());
            double azimuth = Math.PI + Math.atan2(normal.x(), normal.z());
            double u = azimuth / (2 * Math.PI);
            double v = inclination / Math.PI;

            return new Hit(hit, u , v,  t1, material, normal);
        }
        return null;
    }
}
