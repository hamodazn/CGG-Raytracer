package cgg.a09;

import cgtools.*;
import static cgg.a09.Main.*;

import cgg.a09.Main;


public class SamplerFactory {

    public static Sampler checkboard() {

      return new Sampler() {
     
        @Override
        public Color getColor(double u, double v) {
          Sampler s = new Polkadots();
          double size = 10;
          int ui = (int)((u % 1) * size);
          int vi = (int)((v % 1) * size);
          if ((ui + vi) % 2 == 0)
            return s.getColor(ui, vi);
          else
            return new Color(0, 0, 0);
        }
      };
    }


/*     public static Sampler polkadots(){
      return new Sampler() {
        public Color getColor(double x, double y) {
          double radius = 0.3;
          double ui = x % 1f;
          double vi = y % 1f;
  
          if(circleCheck(ui, vi, 0.25, radius) || circleCheck(ui, vi, 0.75, radius) ) {
              return Vector.blue;
          } else {
              return Vector.green;
          }
        }
      };
    } */

    public static Sampler polkadots(){
      return new Sampler(){
        double size = 0.1;
        Color color = Vector.blue;
        public Color getColor(double u, double v) {
          if (Math.hypot((u%size) - (size/2), (v%size) - (size/2)) <= (size/3)){
            
            return Vector.hsvToRgb(new Color((v % 1 + u % 1) / 2,1,1));
          } else {
            return new Color(0,0,0);
          }
        }
      };
    }

    public static Sampler white() {

      return new Sampler() {
     
        @Override
        public Color getColor(double u, double v) {
          return Vector.white;
        }
      };
    }

    public static boolean circleCheck(double x, double y, double center, double radius) {
      return Math.pow(x - center, 2) + Math.pow(y - center, 2) < Math.pow(radius, 2);
  }
}


class Polkadots implements Sampler{
  
  double size = 0.1;
  Color color = Vector.blue;

  public Color getColor(double u, double v) {
    if (Math.hypot((u%size) - (size/2), (v%size) - (size/2)) <= (size/3)){
      return color;
    } else {
      return new Color(0,0,0);
    }
  }

}