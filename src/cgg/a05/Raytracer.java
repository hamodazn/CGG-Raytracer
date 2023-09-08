package cgg.a05;

import cgg.a03.Camera;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Vector;

public record Raytracer(Camera camera, Group group) implements Sampler {

    public Color getColor(double x, double y) {
        Direction direction = camera.rayDirection(x, y);
        Ray r = new Ray(new Point(0, 0, 0), direction, 0, Double.POSITIVE_INFINITY);
        return calculateRadiance(group, r, 5);
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