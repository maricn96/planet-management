package com.mi.planetmanagement.exceptions;

public class NoRecordFoundException extends RuntimeException {

    public NoRecordFoundException() {
        super();
    }

    public NoRecordFoundException(String message) {
        super(message);
    }
}
