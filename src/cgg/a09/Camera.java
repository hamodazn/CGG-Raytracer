package cgg.a09;

import cgtools.*;
import static cgtools.Matrix.*;

public class Camera {

protected Point pos; 
protected double theta;
protected double height;
protected double width;
protected double z;
protected Matrix transformation;
 

    public Camera(double theta, double height, double width, Matrix transformation){
    this.theta = theta;
    this.height = height;
    this.width = width;
    this.z = -(width/2) / Math.tan(theta /2);
    this.transformation = transformation;
    this.pos = new Point(0, 0, 0);
    this.pos = multiply(transformation, pos);
    }

    public Ray rayTo(double x, double y, double tmax, double tmin){
        Point abtastpunkt = new Point(x-width/2, -y + height/2, z);
        return new Ray(Vector.normalize(multiply(transformation, Vector.subtract(abtastpunkt, pos))), pos, tmax, tmin);
    }
}