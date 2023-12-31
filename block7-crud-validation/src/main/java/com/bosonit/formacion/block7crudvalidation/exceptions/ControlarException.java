package com.bosonit.formacion.block7crudvalidation.exceptions;

import com.bosonit.formacion.block7crudvalidation.domain.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControlarException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException(EntityNotFoundException ex){
        CustomError customError = new CustomError(new Date(), 404, ex.getMessage());
        return ResponseEntity.status(404).body(customError);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<CustomError> handleUnprocessableEntityException(UnprocessableEntityException ex){
        CustomError customError = new CustomError(new Date(), 422, ex.getMessage());
        return ResponseEntity.status(422).body(customError);
    }
}
