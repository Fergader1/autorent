package com.autorent.infrastructure.adapter.input.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CrearClienteRequest(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El documento es obligatorio")
        String documento,

        String telefono,

        //@Email comprueba el formato del correo. Lo acompañamos de @NotBlank para exigir que tenga contenido.
        // El teléfono sigue siendo opcional.
        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El correo electrónico debe tener un formato válido")
        String email

) {
}

//que hace esta clase?
// Esta clase es un record en Java que representa la solicitud para crear un cliente en una API REST
// Contiene los campos nombre, documento, telefono y email del cliente
// Además, tiene anotaciones de validación para asegurar que los datos sean correctos antes de
// procesarlos en la aplicación. Por ejemplo, se asegura de que el nombre, documento y
// correo electrónico no estén vacíos y que el correo electrónico tenga un formato válido. El teléfono es opcional.