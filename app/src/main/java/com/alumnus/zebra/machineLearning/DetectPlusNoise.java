package com.alumnus.zebra.machineLearning;

import java.util.ArrayList;

public class DetectPlusNoise {

   public ArrayList<DetectedEvent> detectedEvents;
   public ArrayList<NoiseZone> noiseZones;

    public DetectPlusNoise(ArrayList<DetectedEvent> detectedEvents, ArrayList<NoiseZone> noiseZones) {
        this.detectedEvents = detectedEvents;
        this.noiseZones = noiseZones;
    }
}
