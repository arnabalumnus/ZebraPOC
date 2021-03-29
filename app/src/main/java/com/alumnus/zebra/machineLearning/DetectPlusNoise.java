package com.alumnus.zebra.machineLearning;

import java.util.ArrayList;

public class DetectPlusNoise {

    ArrayList<DetectedEvent> detectedEvents;
    ArrayList<NoiseZone> noiseZones;

    public DetectPlusNoise(ArrayList<DetectedEvent> detectedEvents, ArrayList<NoiseZone> noiseZones) {
        this.detectedEvents = detectedEvents;
        this.noiseZones = noiseZones;
    }
}
