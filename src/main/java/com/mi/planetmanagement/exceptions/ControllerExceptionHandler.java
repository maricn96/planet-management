package com.mi.planetmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler(value = PlanetNotFoundException.class)
    public ResponseEntity<Object> handlePlanetNotFoundException(PlanetNotFoundException e) {
        return new ResponseEntity<>("Could not find planet with given id!", HttpStatus.NOT_FOUND);
    }

}
