package com.autorent.infrastructure.adapter.input.rest.dto;

import com.autorent.domain.model.Auto;
import com.autorent.domain.model.EstadoAuto;

import java.math.BigDecimal;

public record AutoResponse(
        Long id,
        String placa,
        String marca,
        String modelo,
        Integer anio,
        BigDecimal precioPorDia,
        EstadoAuto estado
) {

    public static AutoResponse desdeDominio(Auto auto) {
        return new AutoResponse(
                auto.getId(),
                auto.getPlaca(),
                auto.getMarca(),
                auto.getModelo(),
                auto.getAnio(),
                auto.getPrecioPorDia(),
                auto.getEstado()
        );
    }
}

//que hace esta clase?
// Esta clase es un record en Java que representa la respuesta de un auto en una API REST
// Contiene los campos id, placa, marca, modelo, anio, precioPorDia y estado del auto
// Además, tiene un método estático desdeDominio que convierte un objeto
// Auto del dominio en un objeto AutoResponse para ser enviado como respuesta en la API.
