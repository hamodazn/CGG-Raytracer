package cgg.BaseTools;

import cgtools.Direction;
import cgtools.Point;
import cgtools.Vector;

public record Ray(Point origin, Direction direction, double tMin, double tMax) {

    public Point pointAt(double t){
        return Vector.add(origin, Vector.multiply(t, direction));
    }

    public boolean isValid(double t){
        if((t >= tMin) && (t <= tMax)){
            return true;
        }
        return false;
    }
    
}
