package com.autorent.domain.exception;

//extends RuntimeException convierte nuestra clase en un tipo de excepción.
public class AutoNoDisponibleException extends RuntimeException {

    public AutoNoDisponibleException(String mensaje) {
        //super(mensaje) entrega el mensaje al constructor de la clase de la que heredamos.
        super(mensaje);
    }
}