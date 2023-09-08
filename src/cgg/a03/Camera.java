package cgg.a03;

import cgtools.Direction;
import cgtools.Vector;

public class Camera {

    protected double angle;
    protected int w;
    protected int h;
    protected double z;

    public Camera(int w, int h, double angle) {
        this.angle = angle;
        this.w=w;
        this.h=h;
        this.z=(w/2)/Math.tan(angle/2);
    }

    public Direction rayDirection (double x, double y){
        double xd = x - (w/2);
        double yd = -(y - (h/2));
        double z = -(w/2)/Math.tan(angle/2);
        return Vector.normalize(new Direction(xd, yd, z));
    }
}