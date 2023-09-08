package cgg.a09;

import cgtools.*;
import static cgtools.Vector.*;

public record RayTracer(Group scene, Camera camera) implements Sampler{
    
    public Color getColor(double x, double y){
        Ray ray = camera.rayTo(x, y, Double.POSITIVE_INFINITY, 0);
        return radiance(scene, ray, 5);
    }

    private Color radiance(Shape scene, Ray ray, int depth){
        if(depth == 0){
            return Vector.black;
        }
        Hit hit = scene.intersect(ray);
        Material material = hit.material();
        if(material.isReflective()){
            return add(material.emission(ray, hit), Vector.multiply(material.getAlbedo(ray, hit),
             radiance(scene, material.getReflection(ray, hit), depth - 1)));
        }
        else{
            return material.emission(ray, hit);
        }
    }
}

