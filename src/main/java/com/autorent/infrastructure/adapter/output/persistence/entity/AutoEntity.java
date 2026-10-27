package com.autorent.infrastructure.adapter.output.persistence.entity;

import com.autorent.domain.model.EstadoAuto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "autos")
public class AutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String placa;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private Integer anio;

    @Column(name = "precio_por_dia", nullable = false,
            precision = 19, scale = 2)
    private BigDecimal precioPorDia;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAuto estado;

    // Constructor requerido por JPA.
    protected AutoEntity() {
    }

    // Constructor que utilizará nuestro adaptador.
    public AutoEntity(Long id, String placa, String marca,
                      String modelo, Integer anio,
                      BigDecimal precioPorDia, EstadoAuto estado) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
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
}