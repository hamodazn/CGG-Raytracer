package cgg.BaseTools;

import java.util.List;

import cgtools.*;

public class Group implements Shape {

    final List<Shape> shapeList;
    Transformation t;

    public Group(List<Shape> shapeList) {
        this.shapeList = shapeList;
        this.t = new Transformation(Matrix.identity);
    }

    public Group(Transformation t, List<Shape> shapeList) {
        this.shapeList = shapeList;
        this.t = t;
    }

    public Hit intersect(Ray r) {

        r = t.transform(r);

        Hit shortHit = null;
        for (Shape s : shapeList) {
            Hit hit = s.intersect(r);
            if (hit != null) {
                if (shortHit == null || hit.t() < shortHit.t()) {
                    shortHit = hit;
                }
            }
        }

        if (shortHit != null) {
            shortHit = t.transform(shortHit);
        }

        return shortHit;
    }
}