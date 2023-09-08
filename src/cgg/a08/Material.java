package cgg.a08;

import cgtools.Color;

public interface Material {
    
    public Ray scatteredRay(Ray ray, Hit hit);
    
    public Color albedo(Ray ray, Hit hit);

    public Color emission(Ray ray, Hit hit);
}
