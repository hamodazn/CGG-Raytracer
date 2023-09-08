package cgg.a06;

import java.util.ArrayList;
import java.util.List;

import cgg.a05.DiffuseSurface;
import cgg.a05.Hit;
import cgg.a05.Ray;
import cgg.a05.Shape;
import cgtools.Color;
import cgtools.Point;

public record Tree(Point centerPoint) implements Shape {

    protected static Color darkWood = new Color(0.52, 0.37, 0.25);
    protected static Color darkGreen1 = new Color(0.01, 0.2, 0.12);
    protected static Color darkGreen2 = new Color(0, 0.4, 0);
    protected static DiffuseSurface darkWoodMaterial = new DiffuseSurface(darkWood);
    protected static DiffuseSurface darkGreenMaterial1 = new DiffuseSurface(darkGreen1);
    protected static DiffuseSurface darkGreenMaterial2 = new DiffuseSurface(darkGreen2);

    public Hit intersect(Ray r) {
        List<Shape> treeList = new ArrayList<>();
        CylinderLateralSurface treetrunkLateralSurface = new CylinderLateralSurface(darkWoodMaterial, centerPoint, 0.3,
                1);
        Cylinder treetrunk = new Cylinder(treetrunkLateralSurface);

        CylinderLateralSurface leaf1LateralSurface = new CylinderLateralSurface(darkGreenMaterial1,
                new Point(centerPoint.x(), centerPoint.y() + 1, centerPoint.z()), 1.5, 0.5);
        Cylinder leaf1 = new Cylinder(leaf1LateralSurface);

        CylinderLateralSurface leaf2LateralSurface = new CylinderLateralSurface(darkGreenMaterial2,
                new Point(centerPoint.x(), centerPoint.y() + 1.5, centerPoint.z()), 1.2, 0.5);
        Cylinder leaf2 = new Cylinder(leaf2LateralSurface);

        CylinderLateralSurface leaf3LateralSurface = new CylinderLateralSurface(darkGreenMaterial1,
                new Point(centerPoint.x(), centerPoint.y() + 2, centerPoint.z()), 0.9, 0.5);
        Cylinder leaf3 = new Cylinder(leaf3LateralSurface);

        CylinderLateralSurface leaf4LateralSurface = new CylinderLateralSurface(darkGreenMaterial2,
                new Point(centerPoint.x(), centerPoint.y() + 2.5, centerPoint.z()), 0.6, 0.5);
        Cylinder leaf4 = new Cylinder(leaf4LateralSurface);

        CylinderLateralSurface leaf5LateralSurface = new CylinderLateralSurface(darkGreenMaterial2,
                new Point(centerPoint.x(), centerPoint.y() + 3, centerPoint.z()), 0.3, 0.5);
        Cylinder leaf5 = new Cylinder(leaf5LateralSurface);

        treeList.add(treetrunk);
        treeList.add(leaf1);
        treeList.add(leaf2);
        treeList.add(leaf3);
        treeList.add(leaf4);
        treeList.add(leaf5);
        Group treeGroup = new Group(treeList);
        return treeGroup.intersect(r);
    }
}
