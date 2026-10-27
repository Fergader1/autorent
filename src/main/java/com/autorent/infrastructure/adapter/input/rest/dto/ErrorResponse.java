package com.autorent.infrastructure.adapter.input.rest.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String mensaje,
        List<String> detalles
) {
}

//que hace esta clase?
// Esta clase es un record en Java que representa la respuesta de error en una API REST
// Contiene los campos timestamp, status, error, mensaje y detalles del error
// El campo timestamp indica la fecha y hora en que ocurrió el error
// El campo status indica el código de estado HTTP del error
// El campo error indica el tipo de error
// El campo mensaje proporciona un mensaje descriptivo del error
// El campo detalles es una lista de detalles adicionales sobre el error, como mensajes de validación