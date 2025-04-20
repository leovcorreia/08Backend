package com.desafio08_03_crud.clientes.controllers.handlers;

import com.desafio08_03_crud.clientes.dto.CustomError;
import com.desafio08_03_crud.clientes.dto.ValidationError;
import com.desafio08_03_crud.clientes.services.exceptions.ConflictException;
import com.desafio08_03_crud.clientes.services.exceptions.DatabaseException;
import com.desafio08_03_crud.clientes.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(
                Instant.now(),
                status.value(), // (404)
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError error = new CustomError(
                Instant.now(),
                status.value(), // (400)
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError errors = new ValidationError(
                Instant.now(),
                status.value(), // (422)
                "Dados inválidos",
                request.getRequestURI()
        );

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            errors.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(errors);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<CustomError> conflict(ConflictException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        CustomError error = new CustomError(
                Instant.now(),
                status.value(), // (409)
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(error);
    }

}
