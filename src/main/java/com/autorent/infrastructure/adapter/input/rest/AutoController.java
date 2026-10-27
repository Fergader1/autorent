package com.autorent.infrastructure.adapter.input.rest;

import com.autorent.application.port.input.ConsultarAutoUseCase;
import com.autorent.application.port.input.ConsultarAutosDisponiblesUseCase;
import com.autorent.application.port.input.RegistrarAutoUseCase;
import com.autorent.domain.model.Auto;
import com.autorent.infrastructure.adapter.input.rest.dto.AutoResponse;
import com.autorent.infrastructure.adapter.input.rest.dto.CrearAutoRequest;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/autos")
public class AutoController {

    private final RegistrarAutoUseCase registrarAutoUseCase;
    private final ConsultarAutoUseCase consultarAutoUseCase;
    private final ConsultarAutosDisponiblesUseCase consultarDisponiblesUseCase;

    public AutoController(
            RegistrarAutoUseCase registrarAutoUseCase,
            ConsultarAutoUseCase consultarAutoUseCase,
            ConsultarAutosDisponiblesUseCase consultarDisponiblesUseCase) {

        this.registrarAutoUseCase = registrarAutoUseCase;
        this.consultarAutoUseCase = consultarAutoUseCase;
        this.consultarDisponiblesUseCase = consultarDisponiblesUseCase;
    }

    @PostMapping
    public ResponseEntity<AutoResponse> registrar(
            @Valid @RequestBody CrearAutoRequest solicitud) {

        Auto auto = registrarAutoUseCase.registrar(
                solicitud.placa(),
                solicitud.marca(),
                solicitud.modelo(),
                solicitud.anio(),
                solicitud.precioPorDia()
        );

        AutoResponse respuesta = AutoResponse.desdeDominio(auto);

        return ResponseEntity
                .created(URI.create("/api/autos/" + auto.getId()))
                .body(respuesta);
    }

    @GetMapping("/{id}")
    public AutoResponse consultarPorId(@PathVariable("id") Long id) {
        Auto auto = consultarAutoUseCase.consultarPorId(id);

        return AutoResponse.desdeDominio(auto);
    }

    @GetMapping("/disponibles")
    public List<AutoResponse> consultarDisponibles() {
        return consultarDisponiblesUseCase.consultarDisponibles()
                .stream()
                .map(AutoResponse::desdeDominio)
                .toList();
    }
}

//que hace esta clase?
// Esta clase es un controlador REST en Java que maneja las solicitudes relacionadas con los autos en una API REST
// Contiene métodos para registrar un auto, consultar un auto por su ID y consultar los autos disponibles
// Utiliza casos de uso (use cases) para realizar la lógica de negocio y devuelve respuestas en formato JSON a los clientes que consumen la API.
// Además, utiliza anotaciones de Spring para definir las rutas y los métodos HTTP que maneja, así como para validar las solicitudes entrantes

//3. Entiende las anotaciones
//Anotación	Función
//@RestController	Identifica el componente que recibe solicitudes y devuelve datos, en nuestro caso JSON.
//@RequestMapping("/api/autos")	Establece la ruta base del controller.
//@PostMapping	Atiende solicitudes POST para registrar un auto.
//@GetMapping	Atiende solicitudes GET para consultar información.
//@RequestBody	Convierte el JSON recibido en CrearAutoRequest.
//@Valid	Activa las validaciones que pusimos en ese DTO.
//@PathVariable("id")	Obtiene el identificador escrito en la URL.
