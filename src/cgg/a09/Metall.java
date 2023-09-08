package cgg.a09;

import cgtools.Color;
import static cgtools.Vector.*;


import cgtools.*;

public record Metall(Color albedo) implements Material{

    @Override
    public Color emission(Ray r, Hit h) {
        // TODO Auto-generated method stub
        return black;
    }

    @Override
    public boolean isReflective() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Ray getReflection(Ray r, Hit h) {
        Direction normVector = h.normVector(); 
        Direction d = negate(r.dirVector());
        Direction b = multiply(normVector, dotProduct(d, normVector));
        Direction reflection = add(b, add(r.dirVector(), b));
        return new Ray(reflection, h.hit(), Double.POSITIVE_INFINITY, 0.0001);
    }

    @Override
    public Color getAlbedo(Ray r, Hit h) {
        return albedo;
    }

    @Override
    public String toString() {
        return "Metall toString";
    }
    
}
