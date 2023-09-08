package cgg.a11;

import java.util.ArrayList;
import java.util.List;

import cgg.Image;
import cgg.BaseTools.Camera;
import cgg.BaseTools.Group;
import cgg.BaseTools.Light;
import cgg.BaseTools.Raytracer;
import cgg.BaseTools.Shape;
import cgg.BaseTools.World;
import cgg.Lights.DirectionLight;
import cgg.Lights.PointLight;
import cgg.Materials.DiffuseMaterial;
import cgg.Materials.SandedMaterial;
import cgg.Shapes.Background;
import cgg.Shapes.Plane;
import cgg.Shapes.Sphere;
import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public class Main {
    public static void main(String[] args) {
        final int width = 1920;
        final int height = 1080;
        Image a11Image1 = new Image(width, height);
        Image a11Image2 = new Image(width, height);

        Matrix t = Matrix.translation(0, 1, 2);
        Matrix r = Matrix.rotation(Vector.xAxis, -10);
        Matrix m = Matrix.multiply(r, t);

        Camera camera1 = new Camera(Math.PI / 2, new Point(0, 0, 0), Matrix.identity(), width, height);
        Camera camera2 = new Camera(Math.PI / 2, new Point(0, 0, 0), m, width, height);
        List<Shape> compList = new ArrayList<>();

        Background bg = new Background(Vector.black);
        Plane ground = new Plane(new Point(0, -0.5, 0), Vector.yAxis, 10, new SandedMaterial(Vector.gray, 1));
        Sphere sphere1 = new Sphere(0.5, new DiffuseMaterial(Vector.red), new Point(-1, 0, -3));
        Sphere sphere2 = new Sphere(0.5, new DiffuseMaterial(Vector.blue), new Point(1, 0, -3));
        Sphere sphere3 = new Sphere(0.3, new DiffuseMaterial(Vector.green), new Point(0, -0.15, -1.5));
        compList.add(bg);
        compList.add(ground);
        compList.add(sphere1);
        compList.add(sphere2);
        compList.add(sphere3);
        Group completeGroup = new Group(compList);
        World world1 = new World(completeGroup);
        world1.addLight(new PointLight(new Point(-1, 5, -3), Vector.red, 5));
        world1.addLight(new PointLight(new Point(1, 5, -3), Vector.blue, 5));
        world1.addLight(new PointLight(new Point(0, 5, -1.5), Vector.green, 5));
        world1.addLight(new PointLight(new Point(0, 2, -10), Vector.white, 10));
        world1.addLight(new DirectionLight(new Direction(-1, 0, 0), Vector.white, 1));
        World world2 = new World(completeGroup);
        world2.addLight(new PointLight(new Point(-1, 5, -3), Vector.red, 5));
        world2.addLight(new PointLight(new Point(1, 5, -3), Vector.blue, 5));
        world2.addLight(new PointLight(new Point(0, 5, -1.5), Vector.green, 5));
        world2.addLight(new PointLight(new Point(0, 2, -10), Vector.white, 10));
        world2.addLight(new DirectionLight(new Direction(-1, 0, 0), Vector.white, 1));
        Raytracer rtc = new Raytracer(camera1, world1);
        Raytracer rtc2 = new Raytracer(camera2, world2);
        a11Image1.supersampleacc(rtc, 8);
        a11Image2.supersampleacc(rtc2, 8);

        final String a11String1 = "doc/a11-1.png";
        final String a11String2 = "doc/a11-2.png";
        a11Image1.write(a11String1);
        a11Image2.write(a11String2);
        System.out.println("Wrote image: " + a11String1);
        System.out.println("Wrote image: " + a11String2);
    }
}
