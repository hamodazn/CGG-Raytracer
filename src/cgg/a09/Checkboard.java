package cgg.a09;

import cgtools.*;
import cgtools.ImageTexture;

public class Checkboard implements Sampler {

    public Color getColor(double u, double v) {
        Sampler s = new Polkadots();
        double size = 3;
        int ui = (int)((u % 1) * size);
        int vi = (int)((v % 1) * size);
        if ((ui + vi) % 2 == 0)
          return Vector.black;
        else
          /* return sampler.getColor(u, v); */
          return Vector.blue;
      }
}
