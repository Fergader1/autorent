package com.autorent.infrastructure.adapter.input.rest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CrearAlquilerRequest(

        @NotNull(message = "El identificador del cliente es obligatorio")
        @Positive(message = "El identificador del cliente debe ser positivo")
        Long clienteId,

        @NotNull(message = "El identificador del auto es obligatorio")
        @Positive(message = "El identificador del auto debe ser positivo")
        Long autoId,

        @NotNull(message = "La cantidad de días es obligatoria")
        @Positive(message = "La cantidad de días debe ser mayor que cero")
        Integer cantidadDias

) {
}

//que hace esta clase?
// Esta clase es un record en Java que representa la solicitud para crear un alquiler en una API REST
// Contiene los campos clienteId, autoId y cantidadDias del alquiler
// Además, tiene anotaciones de validación para asegurar que los datos sean correctos antes de
// procesarlos en la aplicación. Por ejemplo, se asegura de que el identificador del cliente y del auto
// sean positivos y que la cantidad de días sea mayor que cero.