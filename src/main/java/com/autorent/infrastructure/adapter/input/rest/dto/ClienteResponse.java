package com.autorent.infrastructure.adapter.input.rest.dto;

import com.autorent.domain.model.Cliente;

public record ClienteResponse(
        Long id,
        String nombre,
        String documento,
        String telefono,
        String email
) {

    public static ClienteResponse desdeDominio(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getDocumento(),
                cliente.getTelefono(),
                cliente.getEmail()
        );
    }
}

//que hace esta clase?
// Esta clase es un record en Java que representa la respuesta de un cliente en una API REST
// Contiene los campos id, nombre, documento, telefono y email del cliente
// Además, tiene un método estático desdeDominio que convierte un objeto Cliente del dominio en
// un objeto ClienteResponse para ser enviado como respuesta en la API.