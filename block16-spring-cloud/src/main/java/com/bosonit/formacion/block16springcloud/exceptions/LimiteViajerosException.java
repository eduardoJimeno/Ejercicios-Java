package com.bosonit.formacion.block16springcloud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LimiteViajerosException extends RuntimeException{
    public LimiteViajerosException(String message){
        super(message);
    }
}
