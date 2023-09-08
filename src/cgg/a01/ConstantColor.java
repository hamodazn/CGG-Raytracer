/** @author henrik.tramberend@beuth-hochschule.de */
package cgg.a01;

import cgtools.*;
import static cgtools.Vector.*;

// Represents the contents of an image. Provides the same color for all pixels.
public record ConstantColor(int width, int height) implements Sampler {

  // Returns the color for the given position.
  public Color getColor(double x, double y) {
    int xi = (int) (x/(width/50));
    int yi = (int) (y/(height/50));
    //double u = (x/(width/10)) - xi;
   // double v = (y/(height/10)) - yi;

    if ((xi+yi)%5 ==0)
    return white;
    else{
    return new Color(x/height, 0, 255);
  }
  }
    
  }

