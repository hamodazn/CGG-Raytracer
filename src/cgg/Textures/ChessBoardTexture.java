package cgg.Textures;

import cgtools.Color;
import cgtools.Sampler;
import cgtools.Vector;

public class ChessBoardTexture implements Sampler{

    protected int n;

    public ChessBoardTexture(int n){
        this.n = n;
    }

    public Color getColor(double u, double v) {
        double du = (int) ((u % 2) * n);
        double dv = (int) ((v % 2) * n);
        if((du + dv) % 2 == 0){
            return Vector.black;
        }
        return Vector.white;
    }
    
}
