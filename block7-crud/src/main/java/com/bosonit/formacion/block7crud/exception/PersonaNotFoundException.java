package com.bosonit.formacion.block7crud.exception;

public class PersonaNotFoundException extends RuntimeException{
    public PersonaNotFoundException(String message){
        super(message);
    }
}
