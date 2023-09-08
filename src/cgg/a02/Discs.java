package cgg.a02;

import cgtools.*;

public record Discs(Point discPoint, int radius, Color color) implements Comparable<Discs> {

    public boolean isPointInDisc(Point point) {
        double dx = discPoint.x() - point.x();
        double dy = discPoint.y() - point.y();
        double hypotenuse = Math.sqrt(dx * dx + dy * dy);
        
        if (hypotenuse < radius) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Discs o) {
        return o.radius - this.radius;
    }

}
