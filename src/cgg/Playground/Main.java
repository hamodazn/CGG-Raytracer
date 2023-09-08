package cgg.Playground;

import java.util.ArrayList;
import java.util.List;

import cgg.Image;
import cgg.BaseTools.Camera;
import cgg.BaseTools.Group;

import cgg.BaseTools.Raytracer;
import cgg.BaseTools.Shape;
import cgg.Materials.DiffuseMaterial;
import cgg.Shapes.Background;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public class Main {
    public static void main(String[] args) {
        final int width = 480;
        final int height = 270;
        Image image = new Image(width, height);

        Camera camera = new Camera(Math.PI/2, new Point(0, 0, 0), Matrix.identity, width, height);

        Background background = new Background(Vector.white);
        Square2D sq2d = new Square2D(new Point(0, 1, -10), Vector.zAxis, 1, 1, new DiffuseMaterial(Vector.red));

        List<Shape> compList = new ArrayList<>();
        compList.add(background);
        compList.add(sq2d);

        Group compGroup = new Group(compList);
        Raytracer rtc = new Raytracer(camera, compGroup);
        image.supersampleacc(rtc, 8);   
        final String imageString = "doc/playground.png";
        image.write(imageString);
        System.out.println("Wrote image: " + imageString);

    }
}
