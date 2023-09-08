package cgg.a04;

import cgg.a03.Camera;
import cgg.a03.Hit;
import cgg.a03.Ray;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Util;

public record Raytracer(Camera camera, Group group) implements Sampler {

    public Color getColor(double x, double y) {
        Direction direction = camera.rayDirection(x, y);
        Ray r = new Ray(new Point(0, 0, 0), direction, 0, Double.POSITIVE_INFINITY);
        Hit shapeHits = group.intersect(r);
        return Util.shade(shapeHits.normalVector(), shapeHits.hitColor());
    }
}