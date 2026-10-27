package com.autorent.application.service;

import com.autorent.application.port.input.ConsultarAlquilerUseCase;
import com.autorent.application.port.input.CrearAlquilerUseCase;
import com.autorent.application.port.input.FinalizarAlquilerUseCase;
import com.autorent.application.port.output.AlquilerRepositoryPort;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.domain.exception.AlquilerNoEncontradoException;
import com.autorent.domain.exception.AutoNoEncontradoException;
import com.autorent.domain.exception.ClienteNoEncontradoException;
import com.autorent.domain.model.Alquiler;
import com.autorent.domain.model.Auto;
import com.autorent.domain.model.Cliente;

import java.time.LocalDate;

public class AlquilerService implements CrearAlquilerUseCase,
        ConsultarAlquilerUseCase,
        FinalizarAlquilerUseCase {

    private final ClienteRepositoryPort clienteRepository;
    private final AutoRepositoryPort autoRepository;
    private final AlquilerRepositoryPort alquilerRepository;

    public AlquilerService(ClienteRepositoryPort clienteRepository,
                           AutoRepositoryPort autoRepository,
                           AlquilerRepositoryPort alquilerRepository) {
        this.clienteRepository = clienteRepository;
        this.autoRepository = autoRepository;
        this.alquilerRepository = alquilerRepository;
    }

    @Override
    public Alquiler crear(Long clienteId, Long autoId, int cantidadDias) {

        Cliente cliente = clienteRepository.buscarPorId(clienteId)
                .orElseThrow(
                        () -> new ClienteNoEncontradoException(clienteId)
                );

        Auto auto = autoRepository.buscarPorId(autoId)
                .orElseThrow(
                        () -> new AutoNoEncontradoException(autoId)
                );

        Alquiler alquiler = new Alquiler(
                cliente,
                auto,
                cantidadDias,
                //obtiene la fecha actual según la zona horaria del sistema donde se ejecuta Java.
                // Por ahora, esa será la fecha de inicio del alquiler.
                LocalDate.now()
        );

        autoRepository.guardar(auto);

        return alquilerRepository.guardar(alquiler);
    }

    @Override
    public Alquiler consultarPorId(Long id) {
        return alquilerRepository.buscarPorId(id)
                .orElseThrow(
                        () -> new AlquilerNoEncontradoException(id)
                );
    }

    @Override
    public Alquiler finalizar(Long id) {

        Alquiler alquiler = consultarPorId(id);

        Auto auto = autoRepository.buscarPorId(alquiler.getAutoId())
                .orElseThrow(
                        () -> new AutoNoEncontradoException(
                                alquiler.getAutoId()
                        )
                );

        alquiler.finalizar(auto);

        autoRepository.guardar(auto);

        return alquilerRepository.guardar(alquiler);
    }
}
//hola me puedes decir que es un puerto de entrada y un puerto de salida en el contexto de la arquitectura hexagonal?
//Claro, en el contexto de la arquitectura hexagonal (también conocida como arquitectura de puertos y adaptadores), los conceptos de "puerto de entrada" y "puerto de salida" son fundamentales para entender cómo se estructura la comunicación entre la aplicación y el mundo exterior.