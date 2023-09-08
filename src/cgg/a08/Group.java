package cgg.a08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;

class Transform {
    Matrix toWorld;
    Matrix fromWorld;
    Matrix toWorldN;

    Transform(Matrix toWorld) {
        this.toWorld = toWorld;
        this.fromWorld = Matrix.invert(this.toWorld);
        this.toWorldN = Matrix.transpose(this.fromWorld);
    }

    Ray transform(Ray ray) {
        Direction tfDir = Matrix.multiply(fromWorld, ray.direction);
        Point tfOrigin = Matrix.multiply(fromWorld, ray.origin);
        return new Ray(tfOrigin, tfDir, ray.tMin, ray.tMax);
    }

    Hit transform(Hit hit) {
        Direction tfNormalDir = Matrix.multiply(toWorldN, hit.normalVector());
        Point tfHitPoint = Matrix.multiply(toWorld, hit.hit());
        return new Hit(hit.t(), tfHitPoint, tfNormalDir, hit.material());

    }
}

public class Group implements Shape {

    final List<Shape> shapeList;
    Transform t;

    public Group(Shape... shapeGrp) {
        shapeList = new ArrayList<>();
        this.shapeList.addAll(Arrays.asList(shapeGrp));
    }

    public Group(List<Shape> shapeList) {
        this.shapeList = shapeList;
        this.t = new Transform(Matrix.identity);
    }

    Group(Transform t, List<Shape> shapeList) {
        this.shapeList = shapeList;
        this.t = t;
    }

    public void add(Shape... shape) {
        for (Shape sh : shape) {
            this.shapeList.add(sh);
        }
    }

    public Hit intersect(Ray ray) {
        ArrayList<Hit> hitList = new ArrayList<Hit>();
        if (t != null) {
            ray = t.transform(ray);
        }

        for (Shape shapes : shapeList) {
            Hit hit = shapes.intersect(ray);
            if (hit != null) {
                hitList.add(hit);
            }
        }

        if (hitList.size() == 0) {
            return null;
        }

        Collections.sort(hitList, Comparator.comparing(Hit::t));
        Hit returnHit = hitList.get(0);
        if (t != null) {
            returnHit = t.transform(returnHit);
        }

        return returnHit;
    }
}
