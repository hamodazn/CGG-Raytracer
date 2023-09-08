package cgg.a09;

import cgtools.*;
import cgtools.Matrix;
import static cgtools.Matrix.*;

public class Transformation {
    protected Matrix matrix;
    protected Matrix inversion;

    public Transformation(Matrix m){
        this.matrix = multiply(identity(), m);
        this.inversion = invert(matrix);
    }

    public Ray worldToObject(Ray r){
        return new Ray(multiply(inversion, r.dirVector()), multiply(inversion, r.origin()),  r.tmax(), r.tmin());
    }

    public Hit objectToWorld(Hit h){
        return new Hit(multiply(matrix, h.hit()), h.u(), h.v(), h.t(), h.material(), multiply(inversion,  h.normVector()));
    }
}
