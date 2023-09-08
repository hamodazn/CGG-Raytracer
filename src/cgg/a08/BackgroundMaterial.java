package cgg.a08;

import cgtools.Color;
import cgtools.Vector;

public class BackgroundMaterial implements Material {
    Color backgroundColor;

    public BackgroundMaterial(Color backgroundColor){
        this.backgroundColor = backgroundColor;
    }

    public Ray scatteredRay(Ray r, Hit h) {
        return null;
    }

    public Color albedo(Ray r, Hit h) {
        return Vector.black;
    }

    public Color emission(Ray r, Hit h) {
        return backgroundColor;
    }
}