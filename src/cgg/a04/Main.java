package cgg.a04;

import java.util.ArrayList;
import java.util.List;

import cgg.Image;
import cgg.a03.Camera;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public class Main {
    public static void main(String[] args) {
        final int width = 480;
        final int height = 270;

        Camera camera = new Camera(width, height, Math.PI / 2);

        // Background background = new Background(Vector.black);

        // Shape ground = new Plane(Vector.gray, new Point(0.0, -0.5, 0.0), new
        // Direction(0, 1, 0), 5000);
        // Shape globe1 = new Sphere(0.7, Vector.red, new Point(-1.0, -0.25, -2.5));
        // Shape globe2 = new Sphere(0.5, Vector.green, new Point(0.0, -0.25, -2.5));
        // Shape globe3 = new Sphere(0.7, Vector.blue, new Point(1.0, -0.25, -2.5));
        // List<Shape> shapeList = new ArrayList<>();
        // shapeList.add(background);
        // shapeList.add(ground);
        // shapeList.add(globe1);
        // shapeList.add(globe2);
        // shapeList.add(globe3);

        Background background2 = new Background(Vector.blue);
        Shape ground2 = new Plane(Vector.white, new Point(0, -0.5, -2), new Direction(0, 1, 0), 5);
        Shape snowglobe1 = new Sphere(0.4, Vector.white, new Point(0, 0, -2.5));
        Shape snowglobe2 = new Sphere(0.3, Vector.white, new Point(0, 0.5, -2.5));
        Shape snowglobe3 = new Sphere(0.2, Vector.white, new Point(0, 0.9, -2.4));
        Shape snowEye1 = new Sphere(0.03, Vector.black, new Point(0.1, 0.8, -2));
        Shape snowEye2 = new Sphere(0.03, Vector.black, new Point(-0.1, 0.8, -2));
        Shape snowNose = new Sphere(0.03, Vector.orange, new Point(0, 0.75, -2));
        Shape snowButton1 = new Sphere(0.04, Vector.black, new Point(0, 0.5, -2));
        Shape snowButton2 = new Sphere(0.04, Vector.black, new Point(0, 0.35, -2));
        Shape snowButton3 = new Sphere(0.04, Vector.black, new Point(0, 0.1, -2));
        Shape snowButton4 = new Sphere(0.04, Vector.black, new Point(0, -0.08, -2));

        // Second snowman
       
        Shape snowglobe4 = new Sphere(0.4, Vector.white, new Point(-1.5, -0.1, -3));
        Shape snowglobe5 = new Sphere(0.3, Vector.white, new Point(-1.5, 0.4, -3));
        Shape snowglobe6 = new Sphere(0.2, Vector.white, new Point(-1.5, 0.86, -3));
        Shape snowEye3 = new Sphere(0.03, Vector.black, new Point(-1.15, 0.8, -2.5));
        Shape snowEye4 = new Sphere(0.03, Vector.black, new Point(-1.28, 0.8, -2.5));
        Shape snowNose2 = new Sphere(0.03, Vector.orange, new Point(-1.2, 0.75, -2.5));
        Shape snowButton5 = new Sphere(0.04, Vector.black, new Point(-1.2, 0.5, -2.5));
        Shape snowButton6 = new Sphere(0.04, Vector.black, new Point(-1.2, 0.35, -2.5));
        Shape snowButton7 = new Sphere(0.04, Vector.black, new Point(-1.2, 0.1, -2.5));
        Shape snowButton8 = new Sphere(0.04, Vector.black, new Point(-1.2, -0.08, -2.5));
        
        //third snowman
        Shape snowglobe7 = new Sphere(0.4, Vector.white, new Point(1.5, -0.1, -3));
        Shape snowglobe8 = new Sphere(0.3, Vector.white, new Point(1.5, 0.4, -3));
        Shape snowglobe9 = new Sphere(0.2, Vector.white, new Point(1.5, 0.86, -3));
        Shape snowEye5 = new Sphere(0.03, Vector.black, new Point(1.15, 0.8, -2.5));
        Shape snowEye6 = new Sphere(0.03, Vector.black, new Point(1.28, 0.8, -2.5));
        Shape snowNose3 = new Sphere(0.03, Vector.orange, new Point(1.2, 0.75, -2.5));
        Shape snowButton9 = new Sphere(0.04, Vector.black, new Point(1.2, 0.5, -2.5));
        Shape snowButton10 = new Sphere(0.04, Vector.black, new Point(1.2, 0.35, -2.5));
        Shape snowButton11 = new Sphere(0.04, Vector.black, new Point(1.2, 0.1, -2.5));
        Shape snowButton12 = new Sphere(0.04, Vector.black, new Point(1.2, -0.08, -2.5));


        List<Shape> sceneShapeList = new ArrayList<>();

        sceneShapeList.add(ground2);
        sceneShapeList.add(snowglobe1);
        sceneShapeList.add(snowglobe2);
        sceneShapeList.add(snowglobe3);
        sceneShapeList.add(snowEye1);
        sceneShapeList.add(snowEye2);
        sceneShapeList.add(snowNose);
        sceneShapeList.add(snowButton1);
        sceneShapeList.add(snowButton2);
        sceneShapeList.add(snowButton3);
        sceneShapeList.add(snowButton4);

        // Second snowman
        sceneShapeList.add(snowglobe4);
        sceneShapeList.add(snowglobe5);
        sceneShapeList.add(snowglobe6);
        sceneShapeList.add(snowEye3);
        sceneShapeList.add(snowEye4);
        sceneShapeList.add(snowNose2);
        sceneShapeList.add(snowButton5);
        sceneShapeList.add(snowButton6);
        sceneShapeList.add(snowButton7);
        sceneShapeList.add(snowButton8);
        
        //third snowman
        sceneShapeList.add(snowglobe7);
        sceneShapeList.add(snowglobe8);
        sceneShapeList.add(snowglobe9);
        sceneShapeList.add(snowEye5);
        sceneShapeList.add(snowEye6);
        sceneShapeList.add(snowNose3);
        sceneShapeList.add(snowButton9);
        sceneShapeList.add(snowButton10);
        sceneShapeList.add(snowButton11);
        sceneShapeList.add(snowButton12);
       

      

        Group sceneGroup = new Group(sceneShapeList, background2);
        // Group group = new Group(shapeList, background);
        // Raytracer rt = new Raytracer(camera, group);
        Raytracer rtScene = new Raytracer(camera, sceneGroup);

        // Image filenamea04Spheresimage = new Image(width, height);
        Image filenameSceneGroup = new Image(width, height);

        // filenamea04Spheresimage.supersample(rt);
        filenameSceneGroup.supersample(rtScene);
        // final String filenamea04Spheres = "doc/a04-3-spheres.png";
        final String filenameSceneGroupname = "doc/a04-scene.png";
        // filenamea04Spheresimage.write(filenamea04Spheres);
        filenameSceneGroup.write(filenameSceneGroupname);
        // System.out.println("Wrote image: " + filenamea04Spheres);
        System.out.println("Wrote image: " + filenameSceneGroupname);
    }
}

// Test Background
// Background bg = new Background( Vector.black);
// Ray ray = new Ray(new Point(0, 0, 0), new Direction(0, 0, -1), 0,
// Double.POSITIVE_INFINITY);
// System.out.println(bg.intersect(ray));