package cgg.a09;

import cgtools.*;
import static cgg.a09.Main.*;

public record DiffMaterial(Sampler albedo) implements Material {
    

    @Override
    public boolean isReflective() {
        return true;
    }

    @Override
    public Ray getReflection(Ray r, Hit hit) {
        double x = 1;
        double y = 1;
        double z = 1;
        Random random = new Random();
        while(x*x + y*y + z*z >= 1){
            x = (random.nextDouble()-0.5)*2;
            y = (random.nextDouble()-0.5)*2;
            z = (random.nextDouble()-0.5)*2;
        }
        Direction ran = Vector.direction(Random.random()* 2-1, Random.random() * 2-1, Random.random() * 2-1);
        Direction d = Vector.normalize(Vector.add(ran, hit.normVector()));
        Point origin = new Point(hit.hit().x(), hit.hit().y(), hit.hit().z());
        return new Ray(d, origin, Double.POSITIVE_INFINITY, 0.0001);
    }

    @Override
    public Color getAlbedo(Ray r, Hit hit) {
/*         double x2d = (hit.hit().x() + WIDTH / 2) / WIDTH;
        double y2d = (hit.hit().y() + HEIGHT / 2) / HEIGHT; 
        return albedo.getColor(x2d, y2d); */
        return albedo.getColor(hit.u(), hit.v());
    }

    @Override
    public Color emission(Ray r, Hit hit) {
        return Vector.black; // Vector.multiply(albedo, Vector.dotProduct(r.dirVector(), hit.normVector()));
    }
}