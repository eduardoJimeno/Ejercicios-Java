package com.formacion.bosonit.examen_JPA_cascada.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FacturaNotFoundException extends RuntimeException{
    public FacturaNotFoundException(String message){
        super(message);
    }
}
