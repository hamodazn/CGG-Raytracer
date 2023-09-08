package cgg.a08;


import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Sphere(double radius, Material material, Point center) implements Shape {

    public Hit intersect(Ray ray) {
        Direction x0 = Vector.subtract(ray.origin, center);
        double a = ray.direction.x()*ray.direction.x() + ray.direction.y()*ray.direction.y() + ray.direction.z()*ray.direction.z();
        double b = 2 * ray.direction.x()*x0.x() + 2 * ray.direction.y()*x0.y() + 2 * ray.direction.z() * x0.z();
        double c = (x0.x()*x0.x() + x0.y()*x0.y() + x0.z()*x0.z()) - (radius * radius);
        double dis1 = b * b - 4 * a * c;

        if (dis1 >= 0) {
            double t0 = (-b - Math.sqrt(dis1)) / (2 * a);
            double t1 = (-b + Math.sqrt(dis1)) / (2 * a);
            if (ray.isValid(t0)) {
                Direction nVector0 = Vector.divide(Vector.subtract(ray.pointAt(t0), center), radius);
                return new Hit(t0, ray.pointAt(t0), Vector.normalize(nVector0), material);
            } else if (ray.isValid(t1)) {
                Direction nVector1 = Vector.divide(Vector.subtract(ray.pointAt(t1), center), radius);
                return new Hit(t1, ray.pointAt(t1), Vector.normalize(nVector1), material);
            }
        }
        return null;
    }
}