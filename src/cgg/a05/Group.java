package cgg.a05;

import java.util.List;

public record Group(List<Shape> shapeList, Background backgroundColor) implements Shape {

    public Hit intersect(Ray r) {
        Hit shortHit = null;
        double t = Integer.MAX_VALUE;

        for (int i = 0; i < shapeList.size(); i++) {
            if(shapeList.get(i).intersect(r) == null){
                continue;
            }

            if (shapeList.get(i).intersect(r).t() < t) {
                shortHit = shapeList.get(i).intersect(r);
                t = shapeList.get(i).intersect(r).t();
            }
        }
        if(shortHit == null){
            return backgroundColor.intersect(r);
        }
        return shortHit;
    }

    // public Hit intersect(Ray r){
    //     Hit shortHit = null;
    //     for(Shape s: shapeList){
    //         Hit hit = s.intersect(r);
    //         if( hit != null){
    //             if(shortHit == null || hit.t()< shortHit.t()){
    //                 shortHit = hit;
    //             }
    //         }
    //     }
    //     return shortHit;
    // }
}