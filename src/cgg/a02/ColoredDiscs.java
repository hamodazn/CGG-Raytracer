package cgg.a02;

import java.util.List;
import cgtools.*;

public record ColoredDiscs(List<Discs> disc) implements Sampler {

    public Color getColor(double x, double y) {

        for (Discs d : disc) {
            boolean pointInDisc = d.isPointInDisc(new Point(x, y, 0));
            if (pointInDisc) {
                double gamma = 2.2;
                Color gammaColor = new Color(Math.pow(d.color().r(), 1/gamma),Math.pow(d.color().g(), 1/gamma),Math.pow(d.color().b(), 1/gamma));
                return gammaColor;
            }
        }
        return Vector.black;
    }
}