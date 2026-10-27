package com.autorent.application.service;

import com.autorent.application.port.input.ConsultarClienteUseCase;
import com.autorent.application.port.input.RegistrarClienteUseCase;
import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.domain.exception.ClienteNoEncontradoException;
import com.autorent.domain.exception.DocumentoDuplicadoException;
import com.autorent.domain.model.Cliente;


//Crear el objeto Cliente
//        ↓
//Su constructor valida y limpia los datos
//        ↓
//Consultar si ya existe el documento
//        ↓
//Si existe → lanzar DocumentoDuplicadoException
//Si no existe → guardar y devolver el cliente

public class ClienteService implements RegistrarClienteUseCase,
        ConsultarClienteUseCase {

    private final ClienteRepositoryPort clienteRepository;

    public ClienteService(ClienteRepositoryPort clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente registrar(String nombre, String documento,
                             String telefono, String email) {

        Cliente cliente = new Cliente(
                nombre,
                documento,
                telefono,
                email
        );

        //en lugar del parámetro documento. Así consultamos con el documento ya limpio,
        // porque el constructor de Cliente elimina los espacios de sus extremos.

        if (clienteRepository.existePorDocumento(cliente.getDocumento())) {
            throw new DocumentoDuplicadoException(
                    cliente.getDocumento()
            );
        }

        return clienteRepository.guardar(cliente);
    }

    @Override
    public Cliente consultarPorId(Long id) {
        return clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new ClienteNoEncontradoException(id));
    }
}