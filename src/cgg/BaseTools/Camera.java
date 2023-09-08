package cgg.BaseTools;

import cgtools.Direction;
import cgtools.Matrix;
import cgtools.Point;
import cgtools.Vector;

public record Camera(double angle, Point origin, Matrix matrix, double width, double height) {
    
    public Ray cameraRay(double x, double y){
        double dx = x - (width/2);
        double dy = -(y - (height/2));
        double dz = -((width/2) / (Math.tan(angle/2)));

        Direction direction = Matrix.multiply(matrix, Vector.normalize(new Direction(dx, dy, dz)));
        Point originPoint = Matrix.multiply(matrix, origin);
        return new Ray(originPoint, direction, 0, Double.POSITIVE_INFINITY);
    }
}
