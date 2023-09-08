package cgg.a06;

import java.util.ArrayList;
import java.util.List;
import cgg.a05.Hit;
import cgg.a05.Plane;
import cgg.a05.Ray;
import cgg.a05.Shape;
import cgtools.Point;
import cgtools.Vector;

public record Cylinder(CylinderLateralSurface cls) implements Shape{    

    public Hit intersect(Ray r) {
        Plane discAbove = new Plane(cls.material(), new Point(cls.centerPoint().x(), cls.centerPoint().y() + cls.height(), cls.centerPoint().z()), Vector.yRotation, cls.radius());
        Plane discBelow = new Plane(cls.material(), cls.centerPoint() , Vector.yRotation, cls.radius());
        List<Shape> shapes = new ArrayList<>();
        shapes.add(cls);
        shapes.add(discAbove);
        shapes.add(discBelow);
        Group cylinderGroup = new Group(shapes);
        return cylinderGroup.intersect(r);
    }
}
