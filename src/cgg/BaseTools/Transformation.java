package cgg.BaseTools;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;

public class Transformation {

    Matrix toWorld;
    Matrix fromWorld;
    Matrix toWorldN;

    Transformation(Matrix toWorld) {
        this.toWorld = toWorld;
        this.fromWorld = Matrix.invert(this.toWorld); //Inverse
        this.toWorldN = Matrix.transpose(this.fromWorld); //Transponierte
    }

    Ray transform(Ray ray) {
        Direction newDirection = Matrix.multiply(fromWorld, ray.direction());
        Point newOriginPoint = Matrix.multiply(fromWorld, ray.origin());
        return new Ray(newOriginPoint, newDirection, ray.tMin(), ray.tMax());
    }

    Hit transform(Hit hit) {

        Direction newNormalDirection = Matrix.multiply(toWorldN, hit.normalVector());
        Point newHitPoint = Matrix.multiply(toWorld, hit.point());
        return new Hit(hit.t(), newHitPoint , newNormalDirection , hit.material(), hit.u(), hit.v()); 

    }
}