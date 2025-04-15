package com.devsuperior.dscommerce.controllers.handlers;

import com.devsuperior.dscommerce.dto.CustomError;
import com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(
                Instant.now(), // Quando o erro aconteceu
                status.value(), // Código HTTP (404)
                e.getMessage(), // Mensagem da exceção ("Recurso não encontrado")
                request.getRequestURI() // Caminho da requisição (ex: "/products/95")
        );
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(
                Instant.now(), // Quando o erro aconteceu
                status.value(), // Código HTTP (400)
                e.getMessage(), // Mensagem da exceção ("Falha de integridade referencial")
                request.getRequestURI() // Caminho da requisição (ex: "/products/1")
        );
        return ResponseEntity.status(status).body(err);
    }
}
