package cgg.a02;

import cgtools.*;



public record RandomSampler(Sampler content, int n) implements Sampler{

    public Color getColor(double x, double y){
        Color color = Vector.black;
        for (int i=0; i< n; i++){
            double rx = Math.random();
            double ry = Math.random();
            double xs = x+rx;
            double ys = y+ry;
            color = Vector.add(color, content.getColor(xs, ys));
    }
    return Vector.divide(color, n);
    
}
}
