package cgg.a09;

import cgtools.*;
import static cgtools.Vector.*;

public record Glass(Color albedo, double opticalIndex) implements Material {

    @Override
    public Color emission(Ray r, Hit h) {
        return Vector.black;
    }

    @Override
    public boolean isReflective() {
        return true;
    }

    @Override
    public Color getAlbedo(Ray r, Hit h) {
        return albedo;
    }

    @Override
    public Ray getReflection(Ray r, Hit h) {
        double n1 = 1;
        double n2 =  opticalIndex;
        Direction n = h.normVector();

        if(dotProduct(r.dirVector(), n) > 0){
            n1 =  opticalIndex;
            n2 =  1;
            n = negate(n);
        }    

        if(refract(r, h, n1, n2, n) != null){
            if(Random.random() > schlick(r, n, n1, n2)){
                return refract(r, h, n1, n2, n);
            }
            return reflect(r, h, n);
        }
        return reflect(r, h, n);
    }


    public static Ray reflect(Ray r, Hit h, Direction n){
        Direction normVector = n; 
        Direction d = r.dirVector();
        Direction b = multiply(normVector, dotProduct(d, normVector));
        Direction reflection = add(b, add(r.dirVector(), b)); // subtract(r.dirVector(),multiply(2.0 * dotProduct(r.dirVector(), n), n)); //
        return new Ray(reflection, h.hit(), Double.POSITIVE_INFINITY, 0.0001);
        
    }

    public static double schlick(Ray ray,Direction n, double n1, double n2 ){
        double r0 = Math.pow((n1 - n2) / (n1 + n2), 2);
        return r0 + (1 - r0) * Math.pow(1 + dotProduct(n, ray.dirVector()), 5);
    }

    public static Ray refract(Ray ray, Hit hit, double n1, double n2, Direction n){
        double r = n1 / n2;
        double c = dotProduct(negate(n), ray.dirVector());
        if(1 - r * r * (1 - c * c) <= 0 ){
            return null;
        }
        double discriminant = Math.sqrt(1 - r * r * (1 - c * c));
        Direction refraction = add( multiply(r, ray.dirVector()),
                                    multiply((r * c) - discriminant, n));
        return new Ray(refraction, hit.hit(), Double.POSITIVE_INFINITY, 0.0001);
    } 
}

