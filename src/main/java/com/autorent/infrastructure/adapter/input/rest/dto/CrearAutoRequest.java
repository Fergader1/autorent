package com.autorent.infrastructure.adapter.input.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


//¿Qué es un record?
//Es una forma de Java de declarar objetos destinados a transportar datos.
// Genera automáticamente el constructor y los métodos para consultar sus valores.
// En este caso, se utiliza para representar la información necesaria para crear un auto en la aplicación.
public record CrearAutoRequest(

        @NotBlank(message = "La placa es obligatoria")
        String placa,

        @NotBlank(message = "La marca es obligatoria")
        String marca,

        @NotBlank(message = "El modelo es obligatorio")
        String modelo,

        @NotNull(message = "El año es obligatorio")
        @Min(value = 1900, message = "El año debe ser mayor o igual a 1900")
        Integer anio,

        @NotNull(message = "El precio por día es obligatorio")
        @DecimalMin(
                value = "0",
                inclusive = false,
                message = "El precio por día debe ser mayor que cero"
        )
        @Digits(
                integer = 17,
                fraction = 2,
                message = "El precio admite hasta 17 dígitos enteros y 2 decimales"
        )
        BigDecimal precioPorDia

) {
}

//que hace esta clase?
// Esta clase es un record en Java que representa la solicitud para crear un auto en una API REST
// Contiene los campos placa, marca, modelo, anio y precioPorDia del auto
// Además, tiene anotaciones de validación para asegurar que los datos sean correctos antes de
// procesarlos en la aplicación. Por ejemplo, se asegura de que la placa, marca y
// modelo no estén vacíos, que el año sea mayor o igual a 1900 y que el precio por día sea mayor que cero y tenga un formato adecuado.