package cgg.Lights;

import cgg.BaseTools.Hit;
import cgg.BaseTools.Light;
import cgg.BaseTools.Ray;
import cgg.BaseTools.Shape;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record PointLight(Point sourcePoint, Color lightColor, double radius) implements Light {

    public Color incomingIntensity(Hit hit, Shape scene) {
        Direction posToLight = Vector.subtract(sourcePoint, hit.point());
        double t = Vector.length(posToLight);
        Ray lightRay = new Ray(hit.point(), Vector.normalize(posToLight), 0.00001, t);
        Hit lightHit = scene.intersect(lightRay);
        if (lightHit != null && lightHit.t() < Double.POSITIVE_INFINITY) {
            return Vector.black;
        }
        // Lambertsche Gleichung

        // |p - x|^2
        double nominator = (t / radius) * (t / radius);
        // p-x normalized * n
        posToLight = Vector.normalize(posToLight);
        double rightTerm = Vector.dotProduct(hit.normalVector(), posToLight);
        double fraction = 1 / nominator;
        if (t > radius) {
            fraction = Math.pow(fraction, 1 / fraction);
        }
        return Vector.multiply(fraction * rightTerm, lightColor);
    }
}
