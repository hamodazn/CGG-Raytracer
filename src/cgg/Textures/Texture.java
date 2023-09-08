package cgg.Textures;

import cgtools.Color;
import cgtools.ImageTexture;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Vector;

public class Texture implements Sampler {
    private ImageTexture texture;
    private Matrix transformationMatrix;
    int width;
    int height;

    public Texture(String filename) {
        this.texture = new ImageTexture(filename);
        this.transformationMatrix = Matrix.identity();
        this.width = texture.width;
        this.height = texture.height;
    }

    public Texture(String filename, Matrix transformationMatrix) {
        this.texture = new ImageTexture(filename);
        this.transformationMatrix = transformationMatrix;
        this.width = texture.width;
        this.height = texture.height;
    }

    public Color getColor(double u, double v) {
        Point point = Matrix.multiply(transformationMatrix, Vector.point(u, v, 0));
        u = point.x() - Math.floor(point.x());
        v = point.y() - Math.floor(point.y());
        double r = Math.pow(texture.getColor(u, v).r(), 2.2);
        double g = Math.pow(texture.getColor(u, v).g(), 2.2);
        double b = Math.pow(texture.getColor(u, v).b(), 2.2);
        return new Color(r, g, b);
    }
}
