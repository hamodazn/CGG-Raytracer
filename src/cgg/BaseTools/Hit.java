package cgg.BaseTools;

import cgtools.Direction;
import cgtools.Point;

public class Hit {
    
    protected double t;
    protected Point point;
    protected Direction normalVector;
    protected Material material;
    protected double u;
    protected double v;

    public Hit(double t, Point point, Direction normalVector, Material material){
        this.t = t;
        this.point = point;
        this.normalVector = normalVector;
        this.material = material;
        this.u = 0;
        this.v = 0;
    }

    public Hit(double t, Point point, Direction normalVector, Material material, double u, double v){
        this.t = t;
        this.point = point;
        this.normalVector = normalVector;
        this.material = material;
        this.u = u;
        this.v = v;
    }

    public double t(){
        return t;
    }

    public Point point(){
        return point;
    }

    public Direction normalVector(){
        return normalVector;
    }

    public Material material(){
        return material;
    }

    public double u(){
        return u;
    }

    public double v(){
        return v;
    }
}
