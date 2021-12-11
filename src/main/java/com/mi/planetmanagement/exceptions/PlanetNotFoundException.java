package com.mi.planetmanagement.exceptions;

public class PlanetNotFoundException extends RuntimeException {

    public PlanetNotFoundException() {
        super();
    }

    public PlanetNotFoundException(String message) {
        super(message);
    }

}
