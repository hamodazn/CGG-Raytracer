package cgg.a09;

import cgtools.*;

public interface Material {
    Color emission(Ray r, Hit h);
    boolean isReflective();
    Ray getReflection(Ray r, Hit h);
    Color getAlbedo(Ray r, Hit h);
}
