package com.desafio08_03_crud.clientes.services.exceptions;

import javax.xml.crypto.Data;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg) {
        super(msg);
    }

}
