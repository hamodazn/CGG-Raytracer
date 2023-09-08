package cgg.a08;

import java.util.ArrayList;
import java.util.List;
import cgg.Image;
import cgtools.*;


public class Main {
        public static void main(String[] args) {
                long startTime = System.nanoTime(); //Sey
                final int width = 1280;
                final int height = 720;
                
                Matrix i = Matrix.identity();
                Camera camera1 = new Camera(width, height, Math.PI / 1.5, i);
                Matrix iup2 = Matrix.translation(new Point(2, 0, 0));
                Camera camera2 = new Camera(width, height, Math.PI / 3, iup2);

                Color oceanColor = new Color(0.38, 0.71, 0.95);
                Color nightSky = new Color(0.02, 0.01, 0.25);

                DiffusesMaterial lightyellow = new DiffusesMaterial(Vector.lightyellow);
                DiffusesMaterial black = new DiffusesMaterial(Vector.black);
                DiffusesMaterial lightblue = new DiffusesMaterial(Vector.lightblue);
                DiffusesMaterial green = new DiffusesMaterial(Vector.green);
                DiffusesMaterial pink = new DiffusesMaterial(Vector.pastelpink);
            


                //-----------------------------------------------------------------
                
                //Mein Background
                BackgroundMaterial bgm = new BackgroundMaterial(Vector.white);
                Background bg = new Background(bgm);
                //Meine Plane
                DiffusesMaterial groundMaterial = new DiffusesMaterial(oceanColor);
                Plane ground = new Plane(groundMaterial, new Point(0, -1, -10), Vector.yAxis, 10);
                //Meine Sonne
                BackgroundMaterial skyMaterial = new BackgroundMaterial(Vector.yellow);
                Plane sun = new Plane(skyMaterial, new Point(70, 30, 0), Vector.nyAxis, 100);

                
                Group gr = new Group();
                Matrix pictureTransform = null;
                Transform pictureTranformation = null;
                List<Shape> sList = new ArrayList<>();
                Random random = new Random();
                for(int j = 0; j < 25; j++){
                        double ranPosX = random.nextDouble(-9 , 9);
                        double ranPosY = random.nextDouble(1 , 5);
                        double ranPosZ = random.nextDouble(-5 , 5);

                        double ranAngle = random.nextDouble(0 , 90);
                        
                        Matrix trans = Matrix.translation(ranPosX, ranPosY, ranPosZ);
                        Matrix rotate = Matrix.rotation(new Direction(0, 1, 0), ranAngle);
                        pictureTransform = Matrix.multiply(trans, rotate);
                        if (ranPosY > 2){
                                Matrix rotatez = Matrix.rotation(new Direction(0, 1, 1), ranAngle);
                                pictureTransform = Matrix.multiply(trans, rotatez);
                        }
                        
                        pictureTranformation = new Transform(pictureTransform);
                        sList.add(new Sphere(0.3, pink , new Point(1, -1.6, -7.5)));
                        sList.add(new Sphere(0.3, lightblue, new Point(1.6, -1.6, -7.5)));
                        sList.add(new Sphere(0.3, lightyellow, new Point(2.2, -1.6, -7.5)));
                        sList.add(new Sphere(0.3, green, new Point(2.8, -1.6, -7.5)));
                        gr.add(new Group(pictureTranformation, sList));
                }

                for (Shape sh : sList) {
                        gr.add(sh);
                }
                

        //---------------------------------------------------------------------------------------
                
                List<Shape> allList = new ArrayList<>();
                allList.add(gr);
                
                Group allGroup = new Group(allList);
        
                List<Shape> completeList = new ArrayList<>();
                completeList.add(ground);
                completeList.add(bg);
                completeList.add(sun);
                completeList.add(allGroup);


        //------------------------------------------------------------------
                Group completeGroup = new Group(completeList);

                Raytracer rtc = new Raytracer(camera1, completeGroup);
                Image a08Image = new Image(width, height);
                a08Image.supersampleacc(rtc, 5);
                final String a08String = "doc/a09-benchmark-scene.png";
                a08Image.write(a08String);
                System.out.println("Wrote image: " + a08String);

                long endTime = System.nanoTime();//SEY
                long totalTime = endTime - startTime;
                totalTime = totalTime / 1000000000;
                System.out.println("Time it took: " + totalTime + " seconds.");
        }
}
