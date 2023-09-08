package cgg.Materials;

import cgg.BaseTools.ConstantColor;
import cgg.BaseTools.Hit;
import cgg.BaseTools.Material;
import cgg.BaseTools.Ray;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Sampler;
import cgtools.Vector;

public class ReflectingMaterial implements Material{

    protected Sampler albedo;

    public ReflectingMaterial(Color color){
        this.albedo = new ConstantColor(color);
    }

    public ReflectingMaterial(Sampler texture){
        this.albedo = texture;
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return Vector.black;
    }

    @Override
    public Color albedo(Ray r, Hit h) {
        return albedo.getColor(h.u(), h.v());
    }

    @Override
    public Ray getSecondaryRay(Ray r, Hit h) {
        Direction direction = Vector.subtract(r.direction(), Vector.multiply(Vector.dotProduct(h.normalVector(), r.direction()), h.normalVector()));
        return new Ray(h.point(), Vector.normalize(Vector.add(h.normalVector(), direction)), 0.00001, r.tMax());
    }
    
}
