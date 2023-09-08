package cgg.a09;

import static cgtools.Vector.*;
import cgtools.*;

public record SandedMetall(Color albedo, double spread) implements Material{

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
        double x = 1;
        double y = 1;
        double z = 1;
        while(x*x + y*y + z*z >= 1){
            x = Random.random();
            y = Random.random();
            z = Random.random();
        }
        Direction randomDir = multiply(new Direction(x, y, z), spread);
        Direction normVector = h.normVector(); 
        Direction d = negate(r.dirVector());
        Direction b = multiply(normVector, dotProduct(d, normVector));
        Direction reflection = add(b, add(r.dirVector(), b));
        Direction randomReflection = add(reflection, randomDir);
        return new Ray(randomReflection, h.hit(), Double.POSITIVE_INFINITY, 0.0001);
    }

    @Override
    public Color getAlbedo(Ray r, Hit h) {
        // TODO Auto-generated method stub
        return albedo;
    }
}