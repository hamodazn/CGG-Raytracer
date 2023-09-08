package cgg.a05;

import cgtools.Color;

public interface Material {
    
    public Ray secondaryRay(Ray r, Hit h);
    
    public Color albedo(Ray r, Hit h);

    public Color emission(Ray r, Hit h);
}