package cgg.a09;

import cgtools.Matrix;
import static cgtools.Matrix.*;
import java.util.*;


public record Group(List<Shape> shapes, Transformation transformation) implements Shape {

    public Hit intersect(Ray r){
        r = transformation.worldToObject(r);

        ArrayList<Hit> hits = new ArrayList<>();
        for (Shape s : shapes) {
            Hit h = s.intersect(r);
            if(h != null){
                hits.add(h);
            }
        }
        hits.sort(new HitComparator());

        try {
            return transformation.objectToWorld(hits.get(0));
        } catch (Exception e) {
           
        }
        return null;
    }
}

    class HitComparator implements Comparator<Hit> {

        @Override
        public int compare(Hit h1, Hit h2) {
            if(h1.t() < h2.t()){
                return -1;
            }
            if(h1.t() > h2.t()){
                return 1;
            }
            if(h1.t() == h2.t()){
                return 0;
            }
            else{
                return 0;
            }
        }
    }