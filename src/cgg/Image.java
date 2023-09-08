/** @author henrik.tramberend@beuth-hochschule.de */
package cgg;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import cgtools.*;

public class Image {
  protected double[] array;
  protected int width;
  protected int height;
  protected Random random = new Random();

  public Image(int width, int height) {
    int size = width * height * 3;
    array = new double[size];
    this.width = width;
    this.height = height;
  }

  public void setPixel(int x, int y, Color color) {
    double gamma = 2.2;
    int i = (y * width + x) * 3;
    array[i] = Math.pow(color.r(), 1 / gamma);

    i = (y * width + x) * 3 + 1;
    array[i] = Math.pow(color.g(), 1 / gamma);

    i = (y * width + x) * 3 + 2;
    array[i] = Math.pow(color.b(), 1 / gamma);
  }

  public void write(String filename) {
    ImageWriter.write(filename, array, width, height);
  }

  public class OnePixel implements Callable<Color> {

    Color superColor = new Color(0, 0, 0);
    Sampler s;
    int x;
    int y;
    int samplingPoints;

    public OnePixel(Sampler s, int x, int y, int samplingPoints) {
      this.s = s;
      this.x = x;
      this.y = y;
      this.samplingPoints = samplingPoints;
    }

    @Override
    public Color call() {
      if (samplingPoints > 0) {
        for (int xi = 0; xi < samplingPoints; xi++) {
          for (int yi = 0; yi < samplingPoints; yi++) {
            double rx = Random.random();
            double ry = Random.random();
            double xs = x + (xi + rx) / samplingPoints;
            double ys = y + (yi + ry) / samplingPoints;
            superColor = Vector.add(superColor, s.getColor(xs, ys));
          }
        }
        superColor = Vector.divide(superColor, samplingPoints * samplingPoints);
        return superColor;
      }
      superColor = s.getColor(x, y);
      return superColor;
    }
  }

  public void supersampleacc(Sampler s, int threads) {

    int cores = Runtime.getRuntime().availableProcessors();
    if (threads <= 0 || threads > cores) {
      threads = cores;
    }
    ExecutorService pool = Executors.newFixedThreadPool(threads);
    List<Future<Color>> pixels = new ArrayList<>();
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        // int xs = x;
        // int ys = y;
        pixels.add(pool.submit(new OnePixel(s, x, y, 10)));
      }
    }
    int z = 0;
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        try {
          setPixel(x, y, pixels.get(z).get());
          z++;
        } catch (InterruptedException e) {
          System.out.println(e.getMessage());
        } catch (ExecutionException e) {
          System.out.println(e.getMessage());
        }
      }
    }
    pool.shutdown();
  }

  // setPixel(x, y, s.getColor(x, y));
  public void supersample(Sampler s) {

    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {

        double r = 0;
        double g = 0;
        double b = 0;
        int abtastanzahl = 10;

        for (int xi = 0; xi < abtastanzahl; xi++) {
          for (int yi = 0; yi < abtastanzahl; yi++) {

            double rx = Random.random();
            double ry = Random.random();
            double xs = x + (xi + rx) / abtastanzahl;
            double ys = y + (yi + ry) / abtastanzahl;

            Color supercolor = s.getColor(xs, ys);
            r += supercolor.r();
            g += supercolor.g();
            b += supercolor.b();
          }
        }
        setPixel(x, y, new Color(r / (abtastanzahl * abtastanzahl), g / (abtastanzahl * abtastanzahl),
            b / (abtastanzahl * abtastanzahl)));
      }
    }
  }

  public void sample(Sampler s) {
    for (int x = 0; x != width; x++) {
      for (int y = 0; y != height; y++) {
        setPixel(x, y, s.getColor(x, y));
      }
    }
  }
  // private void notYetImplemented() {
  // System.err.println("Please complete the implementation of class cgg.Image as
  // part of assignment 1.");
  // System.exit(1);
  // }
}
