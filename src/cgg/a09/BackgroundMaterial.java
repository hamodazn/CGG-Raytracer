package cgg.a09;

import cgtools.Color;
import cgtools.Sampler;

public record BackgroundMaterial(Sampler emission) implements Material {

    @Override
    public boolean isReflective() {
        return false;
    }

    @Override
    public Ray getReflection(Ray r, Hit h) {
        return null;
    }

    @Override
    public Color getAlbedo(Ray r, Hit h) {
        return null;
    }

    @Override
    public Color emission(Ray r, Hit h) {
        return emission.getColor(h.u(), h.v());
    }
}
