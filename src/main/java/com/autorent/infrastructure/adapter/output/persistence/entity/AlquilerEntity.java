package com.autorent.infrastructure.adapter.output.persistence.entity;

import com.autorent.domain.model.EstadoAlquiler;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "alquileres")
public class AlquilerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "auto_id", nullable = false)
    private Long autoId;

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private LocalDate fechaFin;

    @Column(name = "cantidad_dias", nullable = false)
    private int cantidadDias;

    @Column(name = "valor_total", nullable = false,
            precision = 19, scale = 2)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAlquiler estado;

    // Constructor requerido por JPA.
    protected AlquilerEntity() {
    }

    // Constructor que utilizará nuestro adaptador.
    public AlquilerEntity(Long id, Long clienteId, Long autoId,
                          LocalDate fechaInicio, LocalDate fechaFin,
                          int cantidadDias, BigDecimal valorTotal,
                          EstadoAlquiler estado) {
        this.id = id;
        this.clienteId = clienteId;
        this.autoId = autoId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadDias = cantidadDias;
        this.valorTotal = valorTotal;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getAutoId() {
        return autoId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public EstadoAlquiler getEstado() {
        return estado;
    }
}