package com.bosonit.formacion.block16springcloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ViajeNoEncontradoException extends RuntimeException {
    public ViajeNoEncontradoException(String message){
        super(message);
    }
}
