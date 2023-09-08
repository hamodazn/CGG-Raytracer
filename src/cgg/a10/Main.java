package cgg.a10;

import java.util.ArrayList;
import java.util.List;
import cgg.Image;
import cgg.BaseTools.Camera;
import cgg.BaseTools.Group;
import cgg.BaseTools.Raytracer;
import cgg.BaseTools.Shape;
import cgg.Materials.DiffuseMaterial;
import cgg.Shapes.Background;
import cgg.Shapes.Cylinder;
import cgg.Shapes.CylinderLateral;
import cgg.Shapes.Plane;
import cgg.Shapes.Sphere;
import cgg.Textures.ChessBoardTexture;
import cgg.Textures.PolkaDotsTexture;
import cgg.Textures.Texture;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public class Main {
    public static void main(String[] args) {
        final int width = 1920;
        final int height = 1080;
        Matrix t = Matrix.translation(0,2,0);
        Matrix r = Matrix.rotation(Vector.xAxis, -20);
        Image a10image = new Image(width, height);
        Image a10image2 = new Image(width, height);
        Camera camera = new Camera(Math.PI / 2, new Point(0, 0, 0), Matrix.identity(), width, height);
        Camera camera2 = new Camera(Math.PI/2, new Point(0, 0, 0), Matrix.multiply(r, t), width, height);

        Background bg = new Background(new Texture("texturefiles/sunsetsky.png"));

        Sphere sphere = new Sphere(1, new DiffuseMaterial(new ChessBoardTexture(70)), new Point(0, 0, -5));
        Plane ground = new Plane(new Point(0, -1, 0), Vector.yAxis, 10, new DiffuseMaterial(
                new PolkaDotsTexture(0.5, Vector.white, Vector.black, Matrix.scaling(15, 15, 15))));
        CylinderLateral cLateral = new CylinderLateral(
                new DiffuseMaterial(new Texture("texturefiles/brookbountyposter.jpeg", Matrix.scaling(15, 15, 15))),
                new Point(3, -1, -5), 1, 10);
        Cylinder cylinder = new Cylinder(cLateral);
        Sphere spherePD = new Sphere(1, new DiffuseMaterial(new ChessBoardTexture(10)), new Point(-3, 0, -5));
        List<Shape> completeList = new ArrayList<>();
        completeList.add(bg);
        completeList.add(cylinder);
        completeList.add(sphere);
        completeList.add(ground);
        completeList.add(spherePD);
        Group completeGroup = new Group(completeList);

        Raytracer rtc = new Raytracer(camera, completeGroup);
        Raytracer rtc2 = new Raytracer(camera2, completeGroup);
        a10image.supersampleacc(rtc, 8);
        a10image2.supersampleacc(rtc2   , 8);
        final String a10String = "doc/a10-1.png";
        final String a10String2 = "doc/a10-2.png";
        a10image.write(a10String);
        a10image2.write(a10String2);

        System.out.println("Wrote image: " + a10String);
        System.out.println("Wrote image: " + a10String2);
    }
}
