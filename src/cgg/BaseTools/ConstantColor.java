package cgg.BaseTools;

import cgtools.Color;
import cgtools.Sampler;

public class ConstantColor implements Sampler{

    private Color color;

    public ConstantColor(Color color){
        this.color = color;
    }

    public Color getColor(double x, double y) {
        return color;
    }

}