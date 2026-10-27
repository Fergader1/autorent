package com.autorent.infrastructure.adapter.input.rest.dto;

import com.autorent.domain.model.Alquiler;
import com.autorent.domain.model.EstadoAlquiler;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AlquilerResponse(
        Long id,
        Long clienteId,
        Long autoId,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        int cantidadDias,
        BigDecimal valorTotal,
        EstadoAlquiler estado
) {

    public static AlquilerResponse desdeDominio(Alquiler alquiler) {
        return new AlquilerResponse(
                alquiler.getId(),
                alquiler.getClienteId(),
                alquiler.getAutoId(),
                alquiler.getFechaInicio(),
                alquiler.getFechaFin(),
                alquiler.getCantidadDias(),
                alquiler.getValorTotal(),
                alquiler.getEstado()
        );
    }
}

//que hace esta clase?
// Esta clase es un record en Java que representa la respuesta de un alquiler en una API REST
// Contiene los campos id, clienteId, autoId, fechaInicio, fechaFin, cantidadDias, valorTotal y estado del alquiler
// Además, tiene un método estático desdeDominio que convierte un objeto Alquiler del dominio en un objeto AlquilerResponse para ser enviado como respuesta en la API.