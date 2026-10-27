package com.autorent.infrastructure.adapter.input.rest;

import com.autorent.application.port.input.ConsultarAlquilerUseCase;
import com.autorent.application.port.input.CrearAlquilerUseCase;
import com.autorent.application.port.input.FinalizarAlquilerUseCase;
import com.autorent.domain.model.Alquiler;
import com.autorent.infrastructure.adapter.input.rest.dto.AlquilerResponse;
import com.autorent.infrastructure.adapter.input.rest.dto.CrearAlquilerRequest;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/alquileres")
public class AlquilerController {

    private final CrearAlquilerUseCase crearAlquilerUseCase;
    private final ConsultarAlquilerUseCase consultarAlquilerUseCase;
    private final FinalizarAlquilerUseCase finalizarAlquilerUseCase;

    public AlquilerController(
            CrearAlquilerUseCase crearAlquilerUseCase,
            ConsultarAlquilerUseCase consultarAlquilerUseCase,
            FinalizarAlquilerUseCase finalizarAlquilerUseCase) {

        this.crearAlquilerUseCase = crearAlquilerUseCase;
        this.consultarAlquilerUseCase = consultarAlquilerUseCase;
        this.finalizarAlquilerUseCase = finalizarAlquilerUseCase;
    }

    @PostMapping
    public ResponseEntity<AlquilerResponse> crear(
            @Valid @RequestBody CrearAlquilerRequest solicitud) {

        Alquiler alquiler = crearAlquilerUseCase.crear(
                solicitud.clienteId(),
                solicitud.autoId(),
                solicitud.cantidadDias()
        );

        AlquilerResponse respuesta =
                AlquilerResponse.desdeDominio(alquiler);

        return ResponseEntity
                .created(URI.create("/api/alquileres/" + alquiler.getId()))
                .body(respuesta);
    }

    @GetMapping("/{id}")
    public AlquilerResponse consultarPorId(
            @PathVariable("id") Long id) {

        Alquiler alquiler = consultarAlquilerUseCase.consultarPorId(id);

        return AlquilerResponse.desdeDominio(alquiler);
    }

    @PutMapping("/{id}/finalizar")
    public AlquilerResponse finalizar(
            @PathVariable("id") Long id) {

        Alquiler alquiler = finalizarAlquilerUseCase.finalizar(id);

        return AlquilerResponse.desdeDominio(alquiler);
    }
}

//que hace esta clase?
// Esta clase es un controlador REST en Java que maneja las solicitudes relacionadas con los alquileres en una API REST.
// Contiene métodos para crear un nuevo alquiler, consultar un alquiler por su ID y finalizar un alquiler.
// Utiliza casos de uso para realizar las operaciones necesarias y devuelve respuestas en formato JSON a los clientes que consumen la API.
