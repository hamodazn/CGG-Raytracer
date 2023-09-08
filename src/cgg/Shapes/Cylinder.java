package cgg.Shapes;

import java.util.ArrayList;
import java.util.List;

import cgg.BaseTools.Group;
import cgg.BaseTools.Hit;
import cgg.BaseTools.Ray;
import cgg.BaseTools.Shape;
import cgtools.*;

public class Cylinder implements Shape{

    protected CylinderLateral cl;

    public Cylinder(CylinderLateral cl){
        this.cl = cl;
    }


    public Hit intersect(Ray r) {
        Plane discAbove = new Plane(new Point(cl.centerPoint().x(), cl.centerPoint().y() + cl.height(), cl.centerPoint().z()), Vector.yRotation, cl.radius(), cl.material());
        Plane discBelow = new Plane( cl.centerPoint() , Vector.yRotation, cl.radius(),cl.material());
        List<Shape> shapes = new ArrayList<>();
        shapes.add(cl);
        shapes.add(discAbove);
        shapes.add(discBelow);
        Group cylinderGroup = new Group(shapes);
        return cylinderGroup.intersect(r);
    }
    
}
