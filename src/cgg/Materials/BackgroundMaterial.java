package cgg.Materials;

import cgg.BaseTools.ConstantColor;
import cgg.BaseTools.Hit;
import cgg.BaseTools.Material;
import cgg.BaseTools.Ray;
import cgtools.Color;
import cgtools.Sampler;

public class BackgroundMaterial implements Material {

    protected Sampler emission;

    public BackgroundMaterial(Color color) {
        this.emission = new ConstantColor(color);
    }

    public BackgroundMaterial(Sampler texture) {
        this.emission = texture;
    }

    public Color emission(Ray r, Hit h) {
        return emission.getColor(h.u(), h.v());
    }

    public Color albedo(Ray r, Hit h) {
        return null;
    }

    public Ray getSecondaryRay(Ray r, Hit h) {
        return null;
    }
}
