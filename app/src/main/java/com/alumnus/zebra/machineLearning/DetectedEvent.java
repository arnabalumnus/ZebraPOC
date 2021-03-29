package com.alumnus.zebra.machineLearning;

public class DetectedEvent {
    int EVENT_FREEFALL;
    int freefallStart;
    int i;
    double maxTsv;
    double minTsv;
    boolean spinDetected;
    int impactType;

    public DetectedEvent(int EVENT_FREEFALL, int freefallStart, int i, double minTsv, boolean spinDetected) {
        this.EVENT_FREEFALL = EVENT_FREEFALL;
        this.freefallStart = freefallStart;
        this.i = i;
        this.minTsv = minTsv;
        this.spinDetected = spinDetected;
    }

    public DetectedEvent(int EVENT_FREEFALL, int freefallStart, int i, double maxTsv, double minTsv, boolean spinDetected) {
        this.EVENT_FREEFALL = EVENT_FREEFALL;
        this.freefallStart = freefallStart;
        this.i = i;
        this.maxTsv = maxTsv;
        this.minTsv = minTsv;
        this.spinDetected = spinDetected;
    }

    public DetectedEvent(int EVENT_FREEFALL, int freefallStart, int i, double maxTsv, double minTsv, int impactType) {
        this.EVENT_FREEFALL = EVENT_FREEFALL;
        this.freefallStart = freefallStart;
        this.i = i;
        this.maxTsv = maxTsv;
        this.minTsv = minTsv;
        this.impactType = impactType;
    }
}
