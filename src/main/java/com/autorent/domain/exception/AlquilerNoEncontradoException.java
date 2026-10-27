package com.autorent.domain.exception;

public class AlquilerNoEncontradoException extends RuntimeException {

    public AlquilerNoEncontradoException(Long id) {
        super("No se encontró un alquiler con el identificador " + id);
    }
}