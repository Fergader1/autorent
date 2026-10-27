package com.autorent.infrastructure.adapter.input.rest;

import com.autorent.domain.exception.AlquilerNoEncontradoException;
import com.autorent.domain.exception.AlquilerYaFinalizadoException;
import com.autorent.domain.exception.AutoNoDisponibleException;
import com.autorent.domain.exception.AutoNoEncontradoException;
import com.autorent.domain.exception.ClienteNoEncontradoException;
import com.autorent.domain.exception.DatosInvalidosException;
import com.autorent.domain.exception.DocumentoDuplicadoException;
import com.autorent.infrastructure.adapter.input.rest.dto.ErrorResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            AutoNoEncontradoException.class,
            ClienteNoEncontradoException.class,
            AlquilerNoEncontradoException.class
    })
    public ResponseEntity<ErrorResponse> manejarNoEncontrado(
            RuntimeException excepcion) {

        return construirRespuesta(
                HttpStatus.NOT_FOUND,
                excepcion.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler({
            AutoNoDisponibleException.class,
            AlquilerYaFinalizadoException.class,
            DocumentoDuplicadoException.class
    })
    public ResponseEntity<ErrorResponse> manejarConflicto(
            RuntimeException excepcion) {

        return construirRespuesta(
                HttpStatus.CONFLICT,
                excepcion.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler(DatosInvalidosException.class)
    public ResponseEntity<ErrorResponse> manejarDatosInvalidos(
            DatosInvalidosException excepcion) {

        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                excepcion.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidaciones(
            MethodArgumentNotValidException excepcion) {

        List<String> detalles = excepcion.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField()
                        + ": " + error.getDefaultMessage())
                .toList();

        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                "Los datos enviados no son válidos",
                detalles
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> manejarJsonInvalido(
            HttpMessageNotReadableException excepcion) {

        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                "El cuerpo de la solicitud falta o contiene datos "
                        + "con un formato incorrecto",
                List.of()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> manejarTipoIncorrecto(
            MethodArgumentTypeMismatchException excepcion) {

        return construirRespuesta(
                HttpStatus.BAD_REQUEST,
                "El parámetro '" + excepcion.getName()
                        + "' tiene un formato incorrecto",
                List.of()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> manejarIntegridad(
            DataIntegrityViolationException excepcion) {

        return construirRespuesta(
                HttpStatus.CONFLICT,
                "No se pudo guardar la información porque incumple "
                        + "una restricción de la base de datos",
                List.of()
        );
    }

    private ResponseEntity<ErrorResponse> construirRespuesta(
            HttpStatus estado,
            String mensaje,
            List<String> detalles) {

        ErrorResponse respuesta = new ErrorResponse(
                LocalDateTime.now(),
                estado.value(),
                estado.getReasonPhrase(),
                mensaje,
                detalles
        );

        return ResponseEntity
                .status(estado)
                .body(respuesta);
    }
}

////que hace esta clase?
// Esta clase es un manejador global de excepciones en una aplicación Spring Boot.
// Su función principal es capturar y manejar diferentes tipos de excepciones que pueden ocurrir durante la ejecución de la aplicación,
// y devolver respuestas HTTP adecuadas a los clientes que consumen la API
// La clase utiliza la anotación @RestControllerAdvice para indicar que es un componente que maneja excepciones a nivel global en los controladores REST.
// Contiene varios métodos anotados con @ExceptionHandler, cada uno de los cuales maneja un tipo específico de excepción y construye una respuesta de error utilizando la clase ErrorResponse.
// Los métodos manejan excepciones como AutoNoEncontradoException, ClienteNoEncontradoException, AlquilerNoEncontradoException,
// AutoNoDisponibleException, AlquilerYaFinalizadoException, DocumentoDuplicadoException, DatosInvalidosException, MethodArgumentNotValidException,
// HttpMessageNotReadableException, MethodArgumentTypeMismatchException y DataIntegrityViolationException.
// Cada método construye una respuesta de error con un código de estado HTTP adecuado (como 404 Not Found, 400 Bad Request o 409 Conflict),
//  un mensaje descriptivo y, en algunos casos, una lista de detalles adicionales sobre el error.
// Finalmente, la clase devuelve la respuesta de error al cliente que realizó la solicitud,
// permitiendo que la API maneje los errores de manera consistente y proporcione información útil sobre los problemas que puedan surgir durante la interacción con la API.