package cgg.a08;


import cgtools.Color;
import cgtools.Sampler;
import cgtools.Vector;

public class Raytracer implements Sampler {
    Camera camera; 
    Group group;

    public Raytracer(Camera camera, Group group){
        this.camera = camera;
        this. group = group;
    }

    

    public Color getColor(double x, double y) {
        Ray cameraRay = camera.rayCalc(x, y);
        return calcRadiance(group, cameraRay, 5);
    }

    public Color calcRadiance(Shape scene, Ray ray, int depth)  {
        if (depth == 0) {
            return Vector.black;
        }
        Hit hit = scene.intersect(ray);

        Ray scatteredRay = hit.material().scatteredRay(ray, hit);
        Color emission = hit.material().emission(ray, hit);
        Color albedoCol = hit.material().albedo(ray, hit);

        if (scatteredRay != null) {
            Color a1 = Vector.add(albedoCol, emission);
            Color radiance = Vector.multiply(a1, calcRadiance(scene, scatteredRay, depth - 1));
            return radiance;
        }
        return emission;
    }
}