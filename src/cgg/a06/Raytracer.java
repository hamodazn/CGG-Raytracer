package cgg.a06;


import cgg.a05.Hit;
import cgg.a05.Ray;
import cgg.a05.Shape;
import cgtools.Color;
import cgtools.Sampler;
import cgtools.Vector;

public record Raytracer(Camera camera, Group group) implements Sampler {

    

    public Color getColor(double x, double y) {
        Ray cameraRay = camera.cameraRay(x, y);
        return calculateRadiance(group, cameraRay, 5);
    }

    public Color calculateRadiance(Shape scene, Ray ray, int depth) {
        if (depth == 0) {
            return Vector.black;
        }
        Hit hit = scene.intersect(ray);

        Ray secondaryRay = hit.material().secondaryRay(ray, hit);
        Color emission = hit.material().emission(ray, hit);
        Color albedo = hit.material().albedo(ray, hit);

        if (secondaryRay != null) {
            Color a1 = Vector.add(albedo, emission);
            Color radiance = Vector.multiply(a1, calculateRadiance(scene, secondaryRay, depth - 1));
            return radiance;
        }
        return emission;
    }
}