package cgg.a03;

import java.util.ArrayList;

import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Sampler;
import cgtools.Util;
import cgtools.Vector;

public record Raytrace(Camera camera, ArrayList<Sphere> spheres) implements Sampler{



public Color getColor(double x, double y){
       Direction direction = camera.rayDirection(x, y);
       Ray ray = new Ray(new Point(0,0,0), direction, 0, Double.POSITIVE_INFINITY);
       Hit shortHit = null;
       double t = Integer.MAX_VALUE;

       for(Sphere s: spheres){
           Hit hit = s.intersect(ray);
           if(hit == null){
               continue;
           }

           if(hit.t() < t){
               shortHit = hit;
               t = hit.t();
           }
       }

       if(shortHit == null){
           return Vector.white;
       } else {
           return Util.shade(shortHit.normalVector(), shortHit.hitColor());
       }
   }
}