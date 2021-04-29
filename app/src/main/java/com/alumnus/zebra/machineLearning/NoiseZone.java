package com.alumnus.zebra.machineLearning;

import org.jetbrains.annotations.NotNull;

public class NoiseZone {

    int noiseStart;
    int i;

    public NoiseZone(int noiseStart, int i) {
        this.noiseStart = noiseStart;
        this.i = i;
    }

    @NotNull
    @Override
    public String toString() {
        return "[" + noiseStart + "," + i + "]";
    }
}
