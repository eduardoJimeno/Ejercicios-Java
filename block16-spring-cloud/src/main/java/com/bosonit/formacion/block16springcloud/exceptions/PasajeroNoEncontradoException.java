package com.bosonit.formacion.block16springcloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PasajeroNoEncontradoException extends RuntimeException{
    public PasajeroNoEncontradoException(String message){
        super(message);
    }
}
