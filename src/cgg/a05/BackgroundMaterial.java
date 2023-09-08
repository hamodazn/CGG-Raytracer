package cgg.a05;

import cgtools.Color;
import cgtools.Vector;

public record BackgroundMaterial(Color backgroundColor) implements Material {

    public Ray secondaryRay(Ray r, Hit h) {
        return null;
    }

    public Color albedo(Ray r, Hit h) {
        return Vector.black;
    }

    public Color emission(Ray r, Hit h) {
        return backgroundColor;
    }
}