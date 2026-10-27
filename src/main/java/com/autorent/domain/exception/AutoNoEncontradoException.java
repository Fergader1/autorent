package com.autorent.domain.exception;

public class AutoNoEncontradoException extends RuntimeException {

    public AutoNoEncontradoException(Long id) {
        super("No se encontró un auto con el identificador " + id);
    }
}