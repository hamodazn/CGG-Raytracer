package cgg.BaseTools;

import cgtools.Color;

public interface Light {
    public Color incomingIntensity(Hit hit, Shape scene);
}
