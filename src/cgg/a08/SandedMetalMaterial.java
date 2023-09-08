package cgg.a08;


import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public class SandedMetalMaterial implements Material {
    Color color ;
    double s;

    public SandedMetalMaterial(Color color, double s){
        this.color = color;
        this.s = s;
    }

    public Ray scatteredRay(Ray ray, Hit hit) {
        double rndX = Random.random() * 2 - 1;
        double rndY = Random.random() * 2 - 1;
        double rndZ = Random.random() * 2 - 1;
        Direction rndDirection = Vector.direction(rndX, rndY, rndZ);

        Direction sDirection = Vector.multiply(Vector.dotProduct(Vector.negate(ray.direction), hit.normalVector()),
                hit.normalVector());

        Direction scatteredRayDir = Vector.add(sDirection, Vector.multiply(rndDirection, s));
        Ray scatteredRay = new Ray(ray.pointAt(hit.t()), scatteredRayDir, 0.00001, Double.POSITIVE_INFINITY);
        Boolean sBoolean = Vector.dotProduct(scatteredRayDir, hit.normalVector()) < 0;

        if (sBoolean) {
            double randomX2 = Random.random() * 2 - 1;
            double randomY2 = Random.random() * 2 - 1;
            double randomZ2 = Random.random() * 2 - 1;
            Direction randomDir2 = Vector.direction(randomX2, randomY2, randomZ2);
            randomDir2 = Vector.normalize(randomDir2);
            Direction scatteredRayDir2 = Vector.add(sDirection, Vector.multiply(randomDir2, s));
            Ray scatteredRay2 = new Ray(ray.pointAt(hit.t()), scatteredRayDir2, 0.00001, Double.POSITIVE_INFINITY);
            return scatteredRay2;
        }

        return scatteredRay;
    }

    public Color albedo(Ray ray, Hit hit) {
        return color;
    }

    public Color emission(Ray ray, Hit hit) {
        return Vector.black;
    }
}
