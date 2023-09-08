package cgg.a05;

import java.util.ArrayList;
import java.util.List;

import cgg.Image;
import cgg.a03.Camera;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Main {

    public static void main(String[] args) {
        final int width = 700;
        final int height = 500;

        Image diffusesphereImage = new Image(width, height);
        Camera camera = new Camera(width, height, Math.PI / 2);
        BackgroundMaterial emissionBackground = new BackgroundMaterial(Vector.gray);
        Background background = new Background(emissionBackground);
        DiffuseSurface diffuseSurface = new DiffuseSurface(Vector.green);
        DiffuseSurface diffuseSurface2 = new DiffuseSurface(Vector.red);
        DiffuseSurface diffuseSurface3 = new DiffuseSurface(Vector.blue);
        DiffuseSurface diffuseSurface4 = new DiffuseSurface(Vector.black);
        DiffuseSurface diffuseSurface5 = new DiffuseSurface(Vector.yellow);
        DiffuseSurface diffuseSurface6 = new DiffuseSurface(Vector.magenta);

        DiffuseSurface diffuseSurfaceground = new DiffuseSurface(Vector.gray);


        Sphere sphere4 = new Sphere(1, diffuseSurface2, new Point(4, -0.25, -5));
        Sphere sphere5 = new Sphere(1, diffuseSurface3, new Point(-4, -0.25, -5));
        Sphere sphereBehind = new Sphere(1.5, diffuseSurface, new Point(0, 2, -7));
        Sphere sphere = new Sphere(1, diffuseSurface4, new Point(-2, 0, -5));
        Sphere sphere2 = new Sphere(1, diffuseSurface5, new Point(2, 2.5, -4));
        Sphere sphere3 = new Sphere(1, diffuseSurface6, new Point(0, 0, -9));
     

        Shape ground = new Plane(diffuseSurfaceground, new Point(0.0, -1, 0.0), new Direction(0, 1, 0), 5000);
        List<Shape> shapeList = new ArrayList<>();

        shapeList.add(sphere4);
        shapeList.add(sphere5);
        shapeList.add(sphereBehind);
        shapeList.add(ground);
        shapeList.add(sphere);
        shapeList.add(sphere2);
        shapeList.add(sphere3);
       

        Group group = new Group(shapeList, background);

        Raytracer rtc = new Raytracer(camera, group);

        diffusesphereImage.supersample(rtc);

        final String filediffuseSphere = "doc/a05-diffuse-spheres.png";
        diffusesphereImage.write(filediffuseSphere);
        System.out.println("Wrote image: " + filediffuseSphere);

    }
}