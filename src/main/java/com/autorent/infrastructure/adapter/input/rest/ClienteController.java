package com.autorent.infrastructure.adapter.input.rest;

import com.autorent.application.port.input.ConsultarClienteUseCase;
import com.autorent.application.port.input.RegistrarClienteUseCase;
import com.autorent.domain.model.Cliente;
import com.autorent.infrastructure.adapter.input.rest.dto.ClienteResponse;
import com.autorent.infrastructure.adapter.input.rest.dto.CrearClienteRequest;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final RegistrarClienteUseCase registrarClienteUseCase;
    private final ConsultarClienteUseCase consultarClienteUseCase;

    public ClienteController(
            RegistrarClienteUseCase registrarClienteUseCase,
            ConsultarClienteUseCase consultarClienteUseCase) {
        this.registrarClienteUseCase = registrarClienteUseCase;
        this.consultarClienteUseCase = consultarClienteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> registrar(
            @Valid @RequestBody CrearClienteRequest solicitud) {

        Cliente cliente = registrarClienteUseCase.registrar(
                solicitud.nombre(),
                solicitud.documento(),
                solicitud.telefono(),
                solicitud.email()
        );

        ClienteResponse respuesta =
                ClienteResponse.desdeDominio(cliente);

        return ResponseEntity
                .created(URI.create("/api/clientes/" + cliente.getId()))
                .body(respuesta);
    }

    @GetMapping("/{id}")
    public ClienteResponse consultarPorId(
            @PathVariable("id") Long id) {

        Cliente cliente = consultarClienteUseCase.consultarPorId(id);

        return ClienteResponse.desdeDominio(cliente);
    }
}

//que hace esta clase?
// Esta clase es un controlador REST en Java que maneja las solicitudes HTTP relacionadas con los clientes en una API REST.
// Contiene dos endpoints: uno para registrar un nuevo cliente (POST /api/clientes)
// y otro para consultar un cliente por su ID (GET /api/clientes/{id}).
// Utiliza los casos de uso RegistrarClienteUseCase y ConsultarClienteUseCase para realizar la lógica de negocio y devuelve
// respuestas en formato JSON a los clientes que consumen la API.