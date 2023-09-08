package cgg.BaseTools;

import cgtools.Color;

public interface Material {
    public Color emission(Ray r, Hit h);
    public Color albedo(Ray r, Hit h);
    public Ray getSecondaryRay(Ray r, Hit h);
    
}
