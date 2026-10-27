package com.autorent.infrastructure.adapter.output.persistence.adapter;

import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.domain.model.Cliente;
import com.autorent.infrastructure.adapter.output.persistence.entity.ClienteEntity;
import com.autorent.infrastructure.adapter.output.persistence.repository.ClienteJpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientePersistenceAdapter implements ClienteRepositoryPort {

    private final ClienteJpaRepository clienteJpaRepository;

    public ClientePersistenceAdapter(
            ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        ClienteEntity entidad = convertirAEntidad(cliente);

        ClienteEntity entidadGuardada = clienteJpaRepository.save(entidad);

        return convertirADominio(entidadGuardada);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteJpaRepository.findById(id)
                .map(this::convertirADominio);
    }

    @Override
    public boolean existePorDocumento(String documento) {
        return clienteJpaRepository.existsByDocumento(documento);
    }

    private ClienteEntity convertirAEntidad(Cliente cliente) {
        return new ClienteEntity(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getDocumento(),
                cliente.getTelefono(),
                cliente.getEmail()
        );
    }

    private Cliente convertirADominio(ClienteEntity entidad) {
        return new Cliente(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getDocumento(),
                entidad.getTelefono(),
                entidad.getEmail()
        );
    }
}
