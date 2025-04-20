package com.desafio08_03_crud.clientes.services.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException(String msg) {
        super(msg);
    }
}
