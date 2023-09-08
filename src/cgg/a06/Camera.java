package cgg.a06;

import cgg.a05.Ray;
import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public record Camera(int width, int height, double angle, Matrix matrix) {

    public Direction cameraDirection(double x, double y){
        double xd = x - (width / 2);
        double yd = -(y - (height / 2));
        double z = -(width / 2) / Math.tan(angle / 2);

        return Vector.normalize(new Direction(xd, yd, z));
    }

    public Ray cameraRay(double x, double y){
        double xd = x - (width / 2);
        double yd = -(y - (height / 2));
        double z = -(width / 2) / Math.tan(angle / 2);

        Point cameraOriginPoint = new Point(0, 0, 0);
        Direction cameraDirection = Vector.normalize(new Direction(xd, yd, z));
        cameraOriginPoint = Matrix.multiply(matrix, cameraOriginPoint);
        cameraDirection  = Matrix.multiply(matrix, cameraDirection);

        return new Ray(cameraOriginPoint, cameraDirection, 0, Double.POSITIVE_INFINITY);
    }
}
