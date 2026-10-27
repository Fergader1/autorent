package com.autorent.domain.model;

import com.autorent.domain.exception.AutoNoDisponibleException;
import com.autorent.domain.exception.DatosInvalidosException;

import java.math.BigDecimal;

public class Auto {

    private Long id;
    private String placa;
    private String marca;
    private String modelo;
    private Integer anio;
    private BigDecimal precioPorDia;
    private EstadoAuto estado;

    // Constructor para registrar un auto nuevo.
    //llama al segundo constructor de la misma clase. Le entrega null como identificador y DISPONIBLE como estado inicial.
    //Así, ambos caminos pasan por las mismas validaciones sin repetir código. Cuando usamos this(...) para llamar a otro constructor,
    // debe ser la primera instrucción del constructor.
    public Auto(String placa, String marca, String modelo,
                Integer anio, BigDecimal precioPorDia) {
        this(null, placa, marca, modelo, anio,
                precioPorDia, EstadoAuto.DISPONIBLE);
    }

    // Constructor para reconstruir un auto con sus datos guardados.
    public Auto(Long id, String placa, String marca, String modelo,
                Integer anio, BigDecimal precioPorDia, EstadoAuto estado) {

        if (placa == null || placa.isBlank()) {
            throw new DatosInvalidosException(
                    "La placa es obligatoria"
            );
        }

        if (marca == null || marca.isBlank()) {
            throw new DatosInvalidosException(
                    "La marca es obligatoria"
            );
        }

        if (modelo == null || modelo.isBlank()) {
            throw new DatosInvalidosException(
                    "El modelo es obligatorio"
            );
        }

        if (anio == null || anio < 1900) {
            throw new DatosInvalidosException(
                    "El año debe ser mayor o igual a 1900"
            );
        }

        if (precioPorDia == null
                || precioPorDia.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DatosInvalidosException(
                    "El precio por día debe ser mayor que cero"
            );
        }

        if (estado == null) {
            throw new DatosInvalidosException(
                    "El estado es obligatorio"
            );
        }

        this.id = id;
        this.placa = placa.trim();
        this.marca = marca.trim();
        this.modelo = modelo.trim();
        this.anio = anio;
        this.precioPorDia = precioPorDia;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAnio() {
        return anio;
    }

    public BigDecimal getPrecioPorDia() {
        return precioPorDia;
    }

    public EstadoAuto getEstado() {
        return estado;
    }

    public boolean estaDisponible() {
        return estado == EstadoAuto.DISPONIBLE;
    }

    public void marcarAlquilado() {
        if (!estaDisponible()) {
            throw new AutoNoDisponibleException(
                    "El auto con placa " + placa
                            + " no está disponible. Estado actual: " + estado
            );
        }

        this.estado = EstadoAuto.ALQUILADO;
    }

    public void marcarDisponible() {
        this.estado = EstadoAuto.DISPONIBLE;
    }
}
