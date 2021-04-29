package com.alumnus.zebra.machineLearning;

public class DetectedEvent {
    int event_type; //EVENT (FREE FALL OR EVENT IMPACT) enum
    int eventStart;
    int count;

    double minTsv;
    boolean spinDetected;

    double maxTsv;
    double dTsv;
    int impactType;

    //EVENT FREE FALL
    public DetectedEvent(int event_type, int eventStart, int count, double minTsv, boolean spinDetected) {
        this.event_type = event_type;
        this.eventStart = eventStart;
        this.count = count;
        this.minTsv = minTsv;
        this.spinDetected = spinDetected;
    }

    //EVENT IMPACT
    public DetectedEvent(int event_type, int eventStart, int count, double maxTsv, double dTsv, int impactType) {
        this.event_type = event_type;
        this.eventStart = eventStart;
        this.count = count;
        this.maxTsv = maxTsv;
        this.dTsv = dTsv;
        this.impactType = impactType;
    }

    @Override
    public String toString() {
        return "DetectedEvent{" +
                "event_type=" + event_type +
                ", eventStart=" + eventStart +
                ", count=" + count +
                ", minTsv=" + minTsv +
                ", spinDetected=" + spinDetected +
                ", maxTsv=" + maxTsv +
                ", dTsv=" + dTsv +
                ", impactType=" + impactType +
                '}';
    }
}
