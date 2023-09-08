package cgg.a08;

import cgtools.Vector;

public class Background implements Shape {
	public Material material;

	public Background(Material material) {
		this.material = material;
	}

    public Hit intersect(Ray r) {
        double tInfinity = Double.POSITIVE_INFINITY;
        if(r.tMax == tInfinity) {
            return new Hit(tInfinity, r.pointAt(tInfinity), Vector.negate(r.direction), material);
        } else {
            return null;
        }
    }
}