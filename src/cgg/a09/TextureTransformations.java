package cgg.a09;

import cgtools.*;
import static cgtools.Matrix.*;
public class TextureTransformations implements Sampler {
    Matrix matrix;
    Sampler sampler;

    public TextureTransformations(Sampler s, Matrix m){
        this.matrix = m;
        this.sampler = s;
    }

    public Color getColor(double x, double y){
        Point point = multiply(matrix, new Point(x, y, 0));

        return sampler.getColor(point.x(), point.y());
    }
    
}
