package cgg.a09;

import cgtools.*;

public record Hit(Point hit, double u, double v, double t, Material material, Direction normVector) {

    public String toString(){
        return "" + hit + " " + normVector;
    }
}


