package com.bosonit.formacion.block7crudvalidation.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlarException {

    @ExceptionHandler(LanzarException.class)
    public ResponseEntity<String> handleLanzarException(LanzarException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
