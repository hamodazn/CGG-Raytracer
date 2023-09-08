package cgg.Materials;

import cgg.BaseTools.ConstantColor;
import cgg.BaseTools.Hit;
import cgg.BaseTools.Material;
import cgg.BaseTools.Ray;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Random;
import cgtools.Sampler;
import cgtools.Vector;

public class DiffuseMaterial implements Material{

    protected Sampler albedo;

    public DiffuseMaterial(Color color){
        this.albedo = new ConstantColor(color);
    }

    public DiffuseMaterial(Sampler texture){
        this.albedo = texture;
    }

    public Color emission(Ray r, Hit h) {
        return Vector.black;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return albedo.getColor(h.u(), h.v());
    }

    @Override
    public Ray getSecondaryRay(Ray r, Hit h) {
        double rndX = Random.random()*2-1;
        double rndY = Random.random()*2-1;
        double rndZ = Random.random()*2-1;

        Direction rndDirection = Vector.direction(rndX, rndY, rndZ);
        Direction direction = Vector.normalize(Vector.add(rndDirection, h.normalVector()));
        return new Ray(h.point(), Vector.normalize(Vector.add(h.normalVector(), direction)), 0.0001, r.tMax());
    }
    
}
