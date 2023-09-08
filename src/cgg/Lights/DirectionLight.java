package cgg.Lights;

import cgg.BaseTools.Hit;
import cgg.BaseTools.Light;
import cgg.BaseTools.Ray;
import cgg.BaseTools.Shape;
import cgtools.Color;
import cgtools.Direction;
import cgtools.Vector;

public record DirectionLight(Direction lightDirection, Color lightColor, double intensity) implements Light{

    public Color incomingIntensity(Hit hit, Shape scene) {
        Ray lightRay = new Ray(hit.point(), Vector.negate(lightDirection), 0.00001 , Double.POSITIVE_INFINITY);
        Hit lightHit = scene.intersect(lightRay);
        if(lightHit != null && lightHit.t() < Double.POSITIVE_INFINITY){
            return Vector.black;
        }
        Color ambient = Vector.multiply(0.1 * intensity, lightColor);
        Color diffuse = Vector.multiply(0.9 * intensity * Vector.dotProduct(Vector.negate(lightDirection), hit.normalVector()), lightColor);
        return Vector.add(ambient, diffuse);
    }

    
}
