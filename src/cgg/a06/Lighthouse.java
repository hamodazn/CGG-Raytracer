package cgg.a06;

import java.util.ArrayList;
import java.util.List;

import cgg.a05.DiffuseSurface;
import cgg.a05.Hit;
import cgg.a05.Ray;
import cgg.a05.Shape;
import cgtools.Point;
import cgtools.Vector;

public record Lighthouse(Point centerPoint) implements Shape {
    protected static DiffuseSurface lighthousewhite = new DiffuseSurface(Vector.white);
    protected static DiffuseSurface lighthousered = new DiffuseSurface(Vector.red);

    public Hit intersect(Ray r) {
        List<Shape> lighthouseList = new ArrayList<>();
        CylinderLateralSurface lighthousels1 = new CylinderLateralSurface(lighthousered, centerPoint, 1.5, 2);
        Cylinder lighthouse1 = new Cylinder(lighthousels1);

        CylinderLateralSurface lighthousels2 = new CylinderLateralSurface(lighthousewhite,
                new Point(centerPoint.x(), centerPoint.y() + lighthousels1.height(), centerPoint.z()), 1.4, 2);
        Cylinder lighthouse2 = new Cylinder(lighthousels2);

        CylinderLateralSurface lighthousels3 = new CylinderLateralSurface(lighthousered, new Point(centerPoint.x(),
                centerPoint.y() + lighthousels2.height() + lighthousels1.height(), centerPoint.z()), 1.3, 2);
        Cylinder lighthouse3 = new Cylinder(lighthousels3);

        CylinderLateralSurface lighthousels4 = new CylinderLateralSurface(lighthousewhite,
                new Point(centerPoint.x(),
                        centerPoint.y() + lighthousels3.height() + lighthousels2.height() + lighthousels1.height(),
                        centerPoint.z()),
                1.2, 2);
        Cylinder lighthouse4 = new Cylinder(lighthousels4);

        CylinderLateralSurface lighthousels5 = new CylinderLateralSurface(
                lighthousered, new Point(centerPoint.x(), centerPoint.y() + lighthousels4.height()
                        + lighthousels3.height() + lighthousels2.height() + lighthousels1.height(), centerPoint.z()),
                1.1, 2);
        Cylinder lighthouse5 = new Cylinder(lighthousels5);

        CylinderLateralSurface lighthousels6 = new CylinderLateralSurface(lighthousewhite,
                new Point(
                        centerPoint.x(), centerPoint.y() + lighthousels5.height() + lighthousels4.height()
                                + lighthousels3.height() + lighthousels2.height() + lighthousels1.height(),
                        centerPoint().z()),
                1, 2);
        Cylinder lighthouse6 = new Cylinder(lighthousels6);

        CylinderLateralSurface lighthousels7 = new CylinderLateralSurface(lighthousered,
                new Point(centerPoint.x(), lighthousels6.centerPoint().y() + lighthousels6.height(), centerPoint.z()),
                1.4, 1);
        Cylinder lighthouse7 = new Cylinder(lighthousels7);

        lighthouseList.add(lighthouse1);
        lighthouseList.add(lighthouse2);
        lighthouseList.add(lighthouse3);
        lighthouseList.add(lighthouse4);
        lighthouseList.add(lighthouse5);
        lighthouseList.add(lighthouse6);
        lighthouseList.add(lighthouse7);

        Group lighthouseGroup = new Group(lighthouseList);
        return lighthouseGroup.intersect(r);
    }

}
