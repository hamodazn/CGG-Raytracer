package cgg.a08;


import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public record Camera(int width, int height, double angle, Matrix matrix) {



    public Ray rayCalc(double x, double y){
        double xd = x - (width / 2);
        double yd = -(y - (height / 2));
        double z = -(width / 2) / Math.tan(angle / 2);

        Point cPoint = new Point(0, 0, 0);
        Direction cDir = Vector.normalize(new Direction(xd, yd, z));
        cPoint = Matrix.multiply(matrix, cPoint);
        cDir  = Matrix.multiply(matrix, cDir);

        return new Ray(cPoint, cDir, 0, Double.POSITIVE_INFINITY);
    }

    public Direction dirCalc(double x, double y){
        double xd = x - (width / 2);
        double yd = -(y - (height / 2));
        double z = -(width / 2) / Math.tan(angle / 2);

        return Vector.normalize(new Direction(xd, yd, z));
    }
}
