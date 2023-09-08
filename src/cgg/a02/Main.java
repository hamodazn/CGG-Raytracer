package cgg.a02;

import java.util.ArrayList;
import java.util.List;

import cgg.Image;
import cgtools.Color;
import cgtools.Point;
import cgtools.Random;

public class Main {

  public static void main(String[] args) {
    final int width = 400;
    final int height = 300;
    final int anzahlKreis = 150;

    Random r = new Random();
    List<Discs> discList = new ArrayList<Discs>();
    for (int i = 0; i < anzahlKreis; i++) {
      Discs discs = new Discs(new Point(r.nextInt(width), r.nextInt(height), 0), r.nextInt(80),
          new Color(r.nextDouble(), r.nextDouble(), r.nextDouble()));

      discList.add(discs);
      discList.sort((o1, o2) -> o1.radius() - o2.radius());
    }

    // Objekterstellung
    // This class instance defines the contents of the image.
    // ConstantColor content = new ConstantColor(black);
    ColoredDiscs cd = new ColoredDiscs(discList);
    // Creates an image and iterates over all pixel positions inside the image.
    // Image image = new Image(width, height);
    Image coloredDiscsImage = new Image(width, height);
    Image superSampingImage = new Image(width, height);

    // Vorherige Abtastschleife (Aufgabe 2.2)
    // for (int x = 0; x != width; x++) {
    // for (int y = 0; y != height; y++) {
    // // Sets the color for one particular pixel.
    // coloredDiscsImage.setPixel(x, y, cd.getColor(x, y));
    // }
    // }

    coloredDiscsImage.sample(cd);
    superSampingImage.supersample(cd);
    
    // Write the image to disk.
    
    final String filenameSuperSamping = "doc/a02-discs-supersampling.png";


    
    superSampingImage.write(filenameSuperSamping);

    
    System.out.println("Wrote image: " + filenameSuperSamping);
  }
}