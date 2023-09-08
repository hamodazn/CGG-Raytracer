package cgg.a06;

import java.util.ArrayList;
import java.util.List;

import cgg.Image;
import cgg.BaseTools.Material;
import cgg.Shapes.Sphere;
import cgg.a05.Background;
import cgg.a05.BackgroundMaterial;
import cgg.a05.DiffuseSurface;

import cgg.a05.Shape;
import cgg.a06.GlassSurface;
import cgg.a05.Plane;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public class Main {
    public static void main(String[] args) {
        final int width = 480;
        final int height = 270;

        // Farben
        Color dragonBallOrange = new Color(0.93, 0.41, 0.12);
        Color woodColor = new Color(0.89, 0.82, 0.75);
        Color cushionColor = new Color(0.44, 0.01, 0.63);
        Color oceanColor = new Color(0, 0.62, 0.77);

        // Materialien
        BackgroundMaterial backgroundMaterial = new BackgroundMaterial(Vector.white);
        DiffuseSurface diffuseSurfaceGround = new DiffuseSurface(oceanColor);
        DiffuseSurface dragonballStarMaterial = new DiffuseSurface(Vector.red);
        DiffuseSurface cushionMaterial = new DiffuseSurface(cushionColor);
        GlassSurface dragonballGlas = new GlassSurface(dragonBallOrange);
        DiffuseSurface tableMaterial = new DiffuseSurface(woodColor);
        DiffuseSurface statue1Material = new DiffuseSurface(woodColor);
        DiffuseSurface statue2Material = new DiffuseSurface(woodColor);
        DiffuseSurface strawhatMaterial = new DiffuseSurface(Vector.yellow);
        DiffuseSurface strawhatMaterialBand = new DiffuseSurface(Vector.red);

        // Shapes für 2. Bild
        Background background = new Background(backgroundMaterial);
        Shape ground = new Plane(diffuseSurfaceGround, new Point(0, -1, 0), new Direction(0, 1, 0), 100000);

        // Bein rechts vorne
        CylinderLateralSurface cylinderlateralsurface1 = new CylinderLateralSurface(statue1Material,
                new Point(3, -1, -5), 0.5, 1);
        Cylinder cylinder1 = new Cylinder(cylinderlateralsurface1);
        CylinderLateralSurface cylinderLateralSurface2 = new CylinderLateralSurface(statue2Material,
                new Point(3, 0, -5), 0.5, 1);
        Cylinder cylinder2 = new Cylinder(cylinderLateralSurface2);
        CylinderLateralSurface cylinderLateralSurface3 = new CylinderLateralSurface(statue1Material,
                new Point(3, 1, -5), 0.5, 1);
        Cylinder cylinder3 = new Cylinder(cylinderLateralSurface3);
        // Bein links vorne
        CylinderLateralSurface cylinderLateralSurface4 = new CylinderLateralSurface(statue1Material,
                new Point(-3, -1, -5), 0.5, 1);
        Cylinder cylinder4 = new Cylinder(cylinderLateralSurface4);
        CylinderLateralSurface cylinderLateralSurface5 = new CylinderLateralSurface(statue2Material,
                new Point(-3, 0, -5), 0.5, 1);
        Cylinder cylinder5 = new Cylinder(cylinderLateralSurface5);
        CylinderLateralSurface cylinderLateralSurface6 = new CylinderLateralSurface(statue1Material,
                new Point(-3, 1, -5), 0.5, 1);
        Cylinder cylinder6 = new Cylinder(cylinderLateralSurface6);
        // Bein rechts hinten
        CylinderLateralSurface cylinderLateralSurface7 = new CylinderLateralSurface(statue1Material,
                new Point(3, -1, -10), 0.5, 1);
        Cylinder cylinder7 = new Cylinder(cylinderLateralSurface7);
        CylinderLateralSurface cylinderLateralSurface8 = new CylinderLateralSurface(statue2Material,
                new Point(3, 0, -10), 0.5, 1);
        Cylinder cylinder8 = new Cylinder(cylinderLateralSurface8);
        CylinderLateralSurface cylinderLateralSurface9 = new CylinderLateralSurface(statue1Material,
                new Point(3, 1, -10), 0.5, 1);
        Cylinder cylinder9 = new Cylinder(cylinderLateralSurface9);
        // Bein rechts hinten
        CylinderLateralSurface cylinderLateralSurface10 = new CylinderLateralSurface(statue1Material,
                new Point(-3, -1, -10), 0.5, 1);
        Cylinder cylinder10 = new Cylinder(cylinderLateralSurface10);
        CylinderLateralSurface cylinderLateralSurface11 = new CylinderLateralSurface(statue2Material,
                new Point(-3, 0, -10), 0.5, 1);
        Cylinder cylinder11 = new Cylinder(cylinderLateralSurface11);
        CylinderLateralSurface cylinderLateralSurface12 = new CylinderLateralSurface(statue1Material,
                new Point(-3, 1, -10), 0.5, 1);
        Cylinder cylinder12 = new Cylinder(cylinderLateralSurface12);

        CylinderLateralSurface tableLateralSurface = new CylinderLateralSurface(tableMaterial, new Point(0, 2, -7.5), 5,
                1);
        Cylinder table = new Cylinder(tableLateralSurface);

        CylinderLateralSurface cushionLateralSurface = new CylinderLateralSurface(cushionMaterial,
                new Point(0, 3, -7.5), 2.5, 1);
        Cylinder cushion = new Cylinder(cushionLateralSurface);

        // Sphere strawhatSpehre = new Sphere(0.5, strawhatMaterial, new Point(3.5, 3.2, -6));
        CylinderLateralSurface strawhatBand = new CylinderLateralSurface(strawhatMaterialBand, new Point(3.5, 3, -6),
                0.51, 0.25);
        Plane strawhatPlane = new Plane(strawhatMaterial, new Point(3.5, 3.1, -6), Vector.yRotation, 1);

        Tree tree1 = new Tree(new Point(-2.5, 3, -10));
        Lighthouse lighthouse1 = new Lighthouse(new Point(8, -1, -25));

        // Dragonball
        double dragonX = 0;
        double dragonY = 5;
        double dragonZ = -7.5;
        Point dragonPoint = new Point(dragonX, dragonY, dragonZ);
        Point star1Point = new Point(dragonX, dragonY + 0.3, dragonZ);
        Point star2Point = new Point(dragonX, dragonY - 0.3, dragonZ);
        Point star3Point = new Point(dragonX + 0.3, dragonY, dragonZ);
        Point star4Point = new Point(dragonX - 0.3, dragonY, dragonZ);
        

        List<Shape> dragonBall4StarList = new ArrayList<>();
        // dragonBall4StarList.add(dragonballWithoutStars);
        // dragonBall4StarList.add(star1);
        // dragonBall4StarList.add(star2);
        // dragonBall4StarList.add(star3);
        // dragonBall4StarList.add(star4);
        Group dragonBall4Star = new Group(dragonBall4StarList);

        List<Shape> strawhatList = new ArrayList<>();
        // strawhatList.add(strawhatSpehre);
        strawhatList.add(strawhatBand);
        strawhatList.add(strawhatPlane);
        Group strawhat = new Group(strawhatList);

        List<Shape> tableList = new ArrayList<>();
        tableList.add(cylinder1);
        tableList.add(cylinder2);
        tableList.add(cylinder3);
        tableList.add(cylinder4);
        tableList.add(cylinder5);
        tableList.add(cylinder6);
        tableList.add(cylinder7);
        tableList.add(cylinder8);
        tableList.add(cylinder9);
        tableList.add(cylinder10);
        tableList.add(cylinder11);
        tableList.add(cylinder12);
        tableList.add(table);
        tableList.add(cushion);
        Group tableGroup = new Group(tableList);

        // Gruppenerstellung
        List<Shape> shapeList = new ArrayList<>();
        shapeList.add(background);
        shapeList.add(ground);
        shapeList.add(tableGroup);
        shapeList.add(dragonBall4Star);
        shapeList.add(tree1);
        shapeList.add(lighthouse1);
        shapeList.add(strawhat);

        Group group1 = new Group(shapeList);

        // Matrixerzeugung & Kamerainitialisierung
        Matrix rotationMatrix = Matrix.identity;
        Matrix translationMatrix1 = Matrix.identity;
        Matrix translationMatrix2 = Matrix.identity;
        rotationMatrix = Matrix.rotation(Vector.xRotation, -20);
        translationMatrix1 = Matrix.translation(new Direction(0, 0, 2));
        translationMatrix2 = Matrix.translation(new Direction(0, 5, 0));

        Matrix m = Matrix.multiply(translationMatrix1, translationMatrix2, rotationMatrix);

        Matrix tMatrix1 = Matrix.translation(new Direction(0, 0, 10));
        Matrix tMatrix2 = Matrix.translation(new Direction(0, 7.5,0));
        Matrix tMatrix3 = Matrix.translation(new Direction(5, 0, 0));
        Matrix rMatrix1 = Matrix.rotation(Vector.yRotation, 30);
        Matrix rMatrix2 = Matrix.rotation(Vector.xRotation, -20);
        Matrix i = Matrix.multiply(rMatrix1,tMatrix3,tMatrix2, tMatrix1,rMatrix2);

        Camera camera1 = new Camera(width, height, Math.PI / 2, m);
        Camera camera2 = new Camera(width, height, Math.PI / 2, i);

        // Bilderzeugung
        Raytracer rtc = new Raytracer(camera1, group1);
        Raytracer rtc2 = new Raytracer(camera2, group1);
        Image glassImage = new Image(width, height);
        Image a07Image = new Image(width, height);
        glassImage.supersample(rtc);
        a07Image.supersample(rtc2);
        final String glassImageString = "doc/a06-camera.png";
        final String a07ImageString = "doc/a06-camera1.png";
        glassImage.write(glassImageString);
        a07Image.write(a07ImageString);
        System.out.println("Wrote image: " + glassImageString);
        System.out.println("Wrote image: " + a07ImageString);
    }
}