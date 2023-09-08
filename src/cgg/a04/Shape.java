package cgg.a04;

import cgg.a03.Hit;
import cgg.a03.Ray;

public interface Shape {
    public Hit intersect (Ray r);
}