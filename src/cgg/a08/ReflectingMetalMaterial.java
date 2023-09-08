package cgg.a08;


import cgtools.Color;
import cgtools.Direction;
import cgtools.Vector;

public class ReflectingMetalMaterial implements Material {
    Color color;

    public ReflectingMetalMaterial(Color color){
        this.color = color;
    }
    public Ray scatteredRay(Ray ray, Hit hit) {

        Direction b = Vector.multiply(Vector.dotProduct(Vector.negate(ray.direction), hit.normalVector()),
                hit.normalVector());
        Direction r = Vector.add(b, b, ray.direction);

        Ray scatteredRay = new Ray(ray.pointAt(hit.t()), r, 0.0001, Double.POSITIVE_INFINITY);
        return scatteredRay;
    }

    public Color albedo(Ray r, Hit h) {
        return color;
    }

    public Color emission(Ray r, Hit h) {
        return Vector.black;
    }
}