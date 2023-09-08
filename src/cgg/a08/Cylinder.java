package cgg.a08;

import java.util.ArrayList;
import java.util.List;

import cgtools.Point;
import cgtools.Vector;

public record Cylinder(CylinderLateralSurface cls) implements Shape{    

    public Hit intersect(Ray ray) {
        Plane discAbove = new Plane(cls.material(), new Point(cls.center().x(), cls.center().y() + cls.height(), cls.center().z()), Vector.yRotation, cls.radius());
        Plane discBelow = new Plane(cls.material(), cls.center() , Vector.yRotation, cls.radius());
        List<Shape> shapes = new ArrayList<>();
        shapes.add(cls);
        shapes.add(discAbove);
        shapes.add(discBelow);
        Group cylinderGroup = new Group(shapes);
        return cylinderGroup.intersect(ray);
    }
}
