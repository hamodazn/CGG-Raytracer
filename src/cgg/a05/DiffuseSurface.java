package cgg.a05;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Vector;

public record DiffuseSurface (Color surfaceColor) implements Material {

    public Ray secondaryRay(Ray r, Hit h) {

        double rndX = Random.random()*2-1;
        double rndY = Random.random()*2-1;
        double rndZ = Random.random()*2-1;

        Direction rndDirection = Vector.direction(rndX, rndY, rndZ);
        Direction directionPoint = Vector.normalize(Vector.add(rndDirection, h.normalVector()));

        Ray secondaryRay = new Ray(r.pointAt(h.t()), directionPoint, 0.0001, r.tMax());
        return secondaryRay;
    }

    public Color albedo(Ray r, Hit h) {
        return surfaceColor;
    }

    public Color emission(Ray r, Hit h) {
        return Vector.black;
    }
}