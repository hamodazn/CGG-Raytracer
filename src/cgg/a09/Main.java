package cgg.a09;

import static cgtools.Matrix.*;
import cgtools.*;
import cgg.Image;
import static cgg.a09.SamplerFactory.*;

import java.util.ArrayList;

public class Main {
  public static final int WIDTH = 480;
  public static final int HEIGHT = 270;

  public static void main(String[] args) {
    Direction xAxis = new Direction(0,1,0);
    Direction zAxis = new Direction(1,0,0);
    Direction yAxis = new Direction(0,0,1);
    Matrix transformations1 = multiply( translation(0, 20,10), rotation(1,0,0, -45));
    Matrix transformations2 =  translation(0, 0, 20); 
    Matrix identity = identity();
    Camera camera = new Camera( Math.PI / 2, HEIGHT, WIDTH, transformations1);
    Camera camera2 = new Camera( Math.PI / 2, HEIGHT, WIDTH, transformations2);

    BackgroundMaterial checkboard = new BackgroundMaterial(new Checkboard());
/*     DiffMaterial eagle = new DiffMaterial(new TextureTransformations(new Texture("textures/eagle.jpg"), multiply(rotation(new Direction(0,1,0),180), rotation(new Direction(1,0,0),180), scaling(3,  1, 0))));
    DiffMaterial eagle2 = new DiffMaterial(new TextureTransformations(new Texture("textures/eagle.jpg"), scaling(3,  1, 1)));
    DiffMaterial aka = new DiffMaterial(new ImageTexture("textures/texture.png")); */
    Material planet = new BackgroundMaterial(new TextureTransformations(new Texture("textures/planet.jpg"), scaling(1, 1, 4)));
    Material planet2 = new BackgroundMaterial(new TextureTransformations(new Texture("textures/planet2.jpg"), scaling(1, 1, 4)));
    Material polka2 = new BackgroundMaterial(new TextureTransformations(polkadots(), scaling(3, 4, 1)));
    BackgroundMaterial panorama = new BackgroundMaterial(new TextureTransformations(new Texture("textures/space2.jpg"), scaling(6, 2, 1)));

    Metall metall = new Metall(Vector.white);
    SandedMetall sanded = new SandedMetall(Vector.gray, 0.5);
    BackgroundMaterial hellblau = new BackgroundMaterial(white()); //new Color(240, 248, 255) new Color(1, 0, 0)
    Glass glass = new Glass(Vector.white, 1.5); // opticalIndex 1.5 verhält sich wie Glass || 1.3 für Wasser
    Glass colorglass = new Glass(new Color(0, 1, 1), 1.5); // opticalIndex 1.5 verhält sich wie Glass || 1.3 für Wasser
    Glass wasser = new Glass(Vector.white, 1.3); // opticalIndex 1.5 verhält sich wie Glass || 1.3 für Wasser

    Shape background = new Background( panorama);

    Shape ground = new Plane(new Point(0.0,-10, -5), new Direction(0, 1, 0), checkboard, 30);
    Shape sphere = new Sphere(new Point(-6, 1, -4), 3, metall);
    Shape sphere2 = new Sphere(new Point(6, 1, -4), 3, metall);
    Shape sphere3 = new Sphere(new Point(-6, 1, 10), 5, polka2);
    Shape sphere4 = new Sphere(new Point(6, 1, 10), 5, polka2);
    Shape cylinder = Zylinder.cappedZylinder(new Point(0,0,0), 3, 1, glass);

    ArrayList<Shape> dballs = new ArrayList<>();
    for(int i = -15; i < 15; i += 2){
      double y = Math.sin(i) * 10;
      dballs.add(new Sphere(new Point(i,y, -15), 1, planet));
      dballs.add(new Sphere(new Point(i,y, -10), 1, metall));
      dballs.add(new Sphere(new Point(i,y, -5), 1, planet2));
      dballs.add(new Sphere(new Point(i,y,0 ), 1, metall));
      dballs.add(new Sphere(new Point(i,y, 5), 1, planet));
      dballs.add(new Sphere(new Point(i,y, 10), 1, planet2));

    }

    Shape balls = new Group(dballs, new Transformation(scaling(1, 1, 1)));

    ArrayList<Shape> shapes = new ArrayList<Shape>();
    shapes.add(background);
    shapes.add(ground);
    shapes.add(sphere);
    shapes.add(sphere2);
    shapes.add(sphere3);
    shapes.add(sphere4);
    shapes.add(balls);

    Group scene = new Group(shapes, new Transformation(identity));
    Group scene2 = new Group(shapes, new Transformation(identity));

    // This class instance defines the contents of the image.
    RayTracer rt = new RayTracer(scene, camera);
    RayTracer rt2 = new RayTracer(scene2, camera2);

    // Creates an image and iterates over all pixel positions inside the image.
    Image image = new Image(WIDTH, HEIGHT, rt);
    Image image2 = new Image(WIDTH, HEIGHT, rt2);
    // Image spheres2 = new Image(WIDTH, HEIGHT, rt2);
    // Write the image to disk.
    final String filename = "doc/a10-1.png";
    final String filename2 = "doc/a10-2.png";

    image.sample(rt);
    image.write(filename);
    
    image2.sample(rt2);
    image2.write(filename2);

    System.out.println("Wrote image: " + filename); 
    System.out.println("Wrote image: " + filename2); 
  }

  public static Color cc(double r, double g, double b){
    return new Color(r / 255, g / 255, b / 255);
  }
}

class Muster implements Sampler {

  @Override
  public Color getColor(double x, double y) {
    // TODO Auto-generated method stub
    return null;
  }
  
}