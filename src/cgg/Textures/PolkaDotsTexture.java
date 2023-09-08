package cgg.Textures;

import cgtools.Color;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Vector;

public class PolkaDotsTexture implements Sampler {

    private double radius;
    private Matrix transformation;
    private Color dotColor;
    private Color backgroundColor;

    public PolkaDotsTexture(double radius, Color dotColor, Color backgroundColor) {
        this.radius = radius;
        this.transformation = Matrix.identity();
        this.dotColor = dotColor;
        this.backgroundColor = backgroundColor;
    }

    public PolkaDotsTexture(double radius, Color dotColor, Color backgroundColor, Matrix transformation) {
        this.radius = radius;
        this.transformation = transformation;
        this.dotColor = dotColor;
        this.backgroundColor = backgroundColor;
    }

    public Color getColor(double x, double y) {
        Point p = Matrix.multiply(transformation, Vector.point(x, y, 0));
        double dx = p.x() - Math.floor(p.x() + 0.5);
        double dy = p.y() - Math.floor(p.y() + 0.5);
        if ((dx * dx) + (dy * dy) > (radius * radius)) {
            return backgroundColor;
        }
        return dotColor;
    }

}
