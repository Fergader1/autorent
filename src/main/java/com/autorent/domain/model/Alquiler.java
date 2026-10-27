package com.autorent.domain.model;

import com.autorent.domain.exception.AlquilerYaFinalizadoException;
import com.autorent.domain.exception.DatosInvalidosException;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Alquiler {

    private Long id;
    private Long clienteId;
    private Long autoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int cantidadDias;
    private BigDecimal valorTotal;
    private EstadoAlquiler estado;

    public Alquiler(Cliente cliente, Auto auto,
                    int cantidadDias, LocalDate fechaInicio) {

        if (cliente == null || auto == null) {
            throw new DatosInvalidosException(
                    "El cliente y el auto son obligatorios"
            );
        }

        if (cliente.getId() == null || auto.getId() == null) {
            throw new DatosInvalidosException(
                    "El cliente y el auto deben estar registrados"
            );
        }

        if (cantidadDias <= 0) {
            throw new DatosInvalidosException(
                    "La cantidad de días debe ser mayor que cero"
            );
        }

        if (fechaInicio == null) {
            throw new DatosInvalidosException(
                    "La fecha de inicio es obligatoria"
            );
        }



        this.clienteId = cliente.getId();
        this.autoId = auto.getId();
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaInicio.plusDays(cantidadDias);
        this.cantidadDias = cantidadDias;
        //Con BigDecimal usamos multiply() para multiplicar:
        this.valorTotal = auto.getPrecioPorDia()
                .multiply(BigDecimal.valueOf(cantidadDias));

        auto.marcarAlquilado();
        this.estado = EstadoAlquiler.ACTIVO;
    }

    public Alquiler(Long id, Long clienteId, Long autoId,
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

    public void finalizar(Auto auto) {
        if (estado == EstadoAlquiler.FINALIZADO) {
            throw new AlquilerYaFinalizadoException(
                    "Este alquiler ya fue finalizado"
            );
        }

        if (auto == null || !autoId.equals(auto.getId())) {
            throw new DatosInvalidosException(
                    "El auto no corresponde a este alquiler"
            );
        }

        auto.marcarDisponible();
        this.estado = EstadoAlquiler.FINALIZADO;
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
