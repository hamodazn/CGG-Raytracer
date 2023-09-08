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

public class SandedMaterial implements Material {
    protected Sampler albedo;
    protected double sandFactor;

    public SandedMaterial(Color color, double sandFactor){
        this.albedo = new ConstantColor(color);
        this.sandFactor = sandFactor;
    }

    public SandedMaterial(Sampler texture, double sandFactor){
        this.albedo = texture;
        this.sandFactor = sandFactor;
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

        Direction direction = Vector.subtract(r.direction(), Vector.multiply(Vector.dotProduct(h.normalVector(), r.direction()), h.normalVector()));
        direction = Vector.normalize(Vector.add(direction, rndDirection));

        if(Vector.dotProduct(direction, h.normalVector())<0){
            Vector.negate(direction);
        }
        return new Ray(h.point(), Vector.normalize(Vector.add(h.normalVector(), direction)), 0.00001, r.tMax());
    }


}
