package cgg.a09;

import cgtools.Color;
import cgtools.ImageTexture;
import cgtools.Sampler;

class Texture implements Sampler {
    ImageTexture texture;

    public Texture(String filename) {
        texture = new ImageTexture(filename);
    }

    public Color getColor(double u, double v) {
        u = u - Math.floor(u);
        v = v - Math.floor(v);

        Color color = texture.getColor(u, v);
        return color;
        /* return new Color(Math.pow(color.r(), 2.2),Math.pow(color.g(), 2.2),Math.pow(color.b(), 2.2)); */
    }
}
