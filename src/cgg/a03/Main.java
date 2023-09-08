package cgg.a03;

import java.util.ArrayList;

import cgg.Image;
import cgtools.*;

public class Main {

  public static void main(String[] args) {
    final int width = 1444;
    final int height = 1080;
    



    Image SpheresImage = new Image(width, height);
    Camera camera = new Camera(width, height, Math.PI / 2);
    ArrayList<Sphere> spheres = new ArrayList<Sphere>();


    spheres.add(new Sphere(1, (new Color(Math.random(), Math.random(), Math.random())), new Point(-8, 0,-10)));
    spheres.add(new Sphere(1, (new Color(Math.random(), Math.random(), Math.random())), new Point(8, 0,-10)));
    spheres.add(new Sphere(1, (new Color(Math.random(), Math.random(), Math.random())), new Point(4, 0,-10)));
    spheres.add(new Sphere(1, (new Color(Math.random(), Math.random(), Math.random())), new Point(-4, 0,-10)));
    
    spheres.add(new Sphere(1.8, (new Color(Math.random(), Math.random(), Math.random())), new Point(-8, 4,-10)));
    spheres.add(new Sphere(1.8, (new Color(Math.random(), Math.random(), Math.random())), new Point(8, 4,-10)));
    spheres.add(new Sphere(1.8, (new Color(Math.random(), Math.random(), Math.random())), new Point(4, 4,-10)));
    spheres.add(new Sphere(1.8, (new Color(Math.random(), Math.random(), Math.random())), new Point(-4, 4,-10)));

    spheres.add(new Sphere(1.8, (new Color(Math.random(), Math.random(), Math.random())), new Point(-8, -4,-10)));
    spheres.add(new Sphere(1.8, (new Color(Math.random(), Math.random(), Math.random())), new Point(8, -4,-10)));
    spheres.add(new Sphere(1.8, (new Color(Math.random(), Math.random(), Math.random())), new Point(4, -4,-10)));
    spheres.add(new Sphere(1.8, (new Color(Math.random(), Math.random(), Math.random())), new Point(-4, -4,-10)));

    spheres.add(new Sphere(1.8, (new Color(Math.random(), Math.random(), Math.random())), new Point(0, -8,-10)));
    spheres.add(new Sphere(2, (new Color(Math.random(), Math.random(), Math.random())), new Point(0, -4,-10)));
    spheres.add(new Sphere(2, (new Color(Math.random(), Math.random(), Math.random())), new Point(0, 0,-10)));
    spheres.add(new Sphere(2, (new Color(Math.random(), Math.random(), Math.random())), new Point(0, 4,-10)));
    spheres.add(new Sphere(2, (new Color(Math.random(), Math.random(), Math.random())), new Point(0, 8,-10)));

  
     
   
  


    
    Raytrace raytrace = new Raytrace(camera, spheres);
    SpheresImage.supersample(raytrace);

    // Write the image to disk.
    final String filenameSpheres = "doc/a03-spheres.png";
    SpheresImage.write(filenameSpheres);
    System.out.println("Wrote image: " + filenameSpheres);
  }
}

// Test Aufgabe 3.2 - 3.3

// System.out.println(camera1.getDirection());
    // Camera camera1 = new Camera(10, 10, Math.PI / 2);
    // Point testCPoint = new Point(0, 0, -2);
    // double radius = 1;
    // Point testOPoint = new Point(0 , 0, 0);
    // Direction d = new Direction(0, 0, -1);
    // double tMin = 0;
    // double tMax = Double.POSITIVE_INFINITY;
    // Ray testRay = new Ray(testOPoint, d, tMin, tMax);
    // Sphere testSphere = new Sphere( radius, Vector.green,testCPoint);
    // System.out.println(testSphere.intersect(testRay));