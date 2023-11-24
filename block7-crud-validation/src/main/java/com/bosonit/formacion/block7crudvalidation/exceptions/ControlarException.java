package com.bosonit.formacion.block7crudvalidation.exceptions;

import com.bosonit.formacion.block7crudvalidation.domain.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControlarException {

    @ExceptionHandler(LanzarException.class)
    public ResponseEntity<String> handleLanzarException(LanzarException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException ex){
        CustomError customError = new CustomError();
        customError.setTimestamp(new Date());
        customError.setHttpCode(404);
        customError.setMensaje(ex.getMessage());
        return ResponseEntity.status(404).body(customError);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<CustomError> handleUnprocessableEntityException(UnprocessableEntityException ex){
        CustomError customError = new CustomError();
        customError.setTimestamp(new Date());
        customError.setHttpCode(422);
        customError.setMensaje(ex.getMessage());
        return ResponseEntity.status(422).body(customError);
    }
}
