package com.mi.planetmanagement.exceptions;

public class SatelliteNotFoundException extends RuntimeException {

    public SatelliteNotFoundException() {
        super();
    }

    public SatelliteNotFoundException(String message) {
        super(message);
    }
}
