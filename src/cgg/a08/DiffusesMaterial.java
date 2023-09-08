package cgg.a08;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public class DiffusesMaterial implements Material {
    Color albedoCol;

    public DiffusesMaterial(Color albedoCol){
        this.albedoCol = albedoCol;
    }
    public Ray scatteredRay(Ray ray, Hit hit) {

        double randomX = Random.random()*2-1;
        double randomY = Random.random()*2-1;
        double randomZ = Random.random()*2-1;

        Direction r = Vector.direction(randomX, randomY, randomZ);
        Direction d = Vector.normalize(Vector.add(r, hit.normalVector()));

        Ray secondaryRay = new Ray(ray.pointAt(hit.t()), d, 0.0001, ray.tMax);
        return secondaryRay;
    }

    public Color albedo(Ray r, Hit h) {
        return albedoCol;
    }

    public Color emission(Ray r, Hit h) {
        return Vector.black;
    }
}