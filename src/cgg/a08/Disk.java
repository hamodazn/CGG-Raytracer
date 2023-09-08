package cgg.a08;

import cgtools.*;



public class Disk implements Shape {
  Direction direction;
  Point point;
  Material mat;
  double radius;

  public Disk(Point point, Direction direction, Material mat, Double radius) {
      this.point = point;
      this.direction = direction;
      this.mat = mat;
      this.radius = radius;
  }

  @Override
  public Hit intersect(Ray ray) {
      // TODO Auto-generated method stub
      double denominator = Vector.dotProduct(ray.direction, direction);

      if(denominator != 0) {
          double t = (Vector.dotProduct(point, direction) - Vector.dotProduct(ray.origin, direction)) / denominator;

          if(ray.isValid(t)) {
              Point intersecPoint = ray.pointAt(t);
              if(Vector.dotProduct(Vector.subtract(intersecPoint, point), Vector.subtract(intersecPoint, point)) > (radius * radius)){
                  return null;
              } else {
                  Hit hit = new Hit(t, intersecPoint, direction, mat);
                  return hit;
              }
          } else {
              return null;
          }
      }
      return null;
  
  }
  
}
