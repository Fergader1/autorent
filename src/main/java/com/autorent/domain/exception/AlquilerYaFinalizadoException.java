package com.autorent.domain.exception;

public class AlquilerYaFinalizadoException extends RuntimeException {

    public AlquilerYaFinalizadoException(String mensaje) {
        super(mensaje);
    }
}
