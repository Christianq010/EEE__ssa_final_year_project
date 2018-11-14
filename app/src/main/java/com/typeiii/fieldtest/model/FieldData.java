package com.typeiii.fieldtest.model;

import java.io.Serializable;

public class FieldData implements Serializable {
    public int signalStrength;
    public double latitude;
    public double longitude;
    public String timestamp;
    public String deviceName;

    public void setSignalStrength(int signalStrength) {
        this.signalStrength = signalStrength;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
