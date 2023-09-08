package cgg.Shapes;

import cgg.BaseTools.ConstantColor;
import cgg.BaseTools.Hit;
import cgg.BaseTools.Material;
import cgg.BaseTools.Ray;
import cgg.BaseTools.Shape;
import cgg.Materials.BackgroundMaterial;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Vector;

public class Background implements Shape {
    public final Sampler background;
    public final Material material;

    public Background(Color color) {
        this.background = new ConstantColor(color);
        this.material = new BackgroundMaterial(background);
    }

    public Background(Sampler texture) {
        this.background = texture;
        this.material = new BackgroundMaterial(background);
    }

    public Hit intersect(Ray r) {
        Point x = Vector.point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        Direction normal = Vector.negate(r.direction());
        double azi = Math.PI + Math.atan2(r.direction().x(), r.direction().z());
        double inc = Math.acos(r.direction().y());
        double u = azi / (2 * Math.PI);
        double v = inc / Math.PI;
        return new Hit(Double.POSITIVE_INFINITY, x, normal, material, u, v);
    }

}
