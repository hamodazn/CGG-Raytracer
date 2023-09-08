package cgg.Playground;

import cgg.BaseTools.Hit;
import cgg.BaseTools.Material;
import cgg.BaseTools.Ray;
import cgg.BaseTools.Shape;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Square2D implements Shape {

    protected Point point;
    protected Direction normal;
    protected double width;
    protected double depth;
    protected Material material;

    public Square2D(Point point, Direction normal, double width, double depth, Material material){
        this.point = point;
        this.normal = Vector.normalize(normal);
        this.width = width;
        this.depth = depth;
        this.material = material;
    }

    public Hit intersect(Ray r) {
        Point origin = r.origin();
        Direction dir = r.direction();
        Point p0 = Vector.add(Vector.direction(-width/2.0, -0.001, -depth/2.0), point);
        Point p1 = Vector.add(Vector.direction(width/2.0, 0.001, depth/2.0), point);
        double x0 = p0.x();
        double y0 = p0.y();
        double z0 = p0.z();
        double x1 = p1.x();
        double y1 = p1.y();
        double z1 = p1.z();
        double dn = Vector.dotProduct(r.direction(), normal);
        double t = Vector.dotProduct(Vector.subtract(point, r.origin()), normal) / dn;
        if (!r.isValid(t)) {
            return null;
        }
        double x = origin.x() + t * dir.x();
        double z = origin.z() + t * dir.z();
        if(x < x0 || x > x1 || z < z0 || z > z1) {
            return null;
        }
        Point px = r.pointAt(t);
        double u = (z - z0) / (z1 - z0);
        double v = (x - x0) / (x1 - x0);
        return new Hit(t, px, normal, material, u, v);
    }

}