package cgg.a05;

import cgtools.Direction;
import cgtools.Point;

public record Hit(double t, Point hit, Direction normalVector, Material material) {
}