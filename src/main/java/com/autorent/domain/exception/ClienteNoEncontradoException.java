package com.autorent.domain.exception;

public class ClienteNoEncontradoException extends RuntimeException {

    public ClienteNoEncontradoException(Long id) {
        super("No se encontró un cliente con el identificador " + id);
    }
}