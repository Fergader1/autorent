package com.autorent.infrastructure.adapter.output.persistence.repository;

import com.autorent.infrastructure.adapter.output.persistence.entity.ClienteEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteJpaRepository
        extends JpaRepository<ClienteEntity, Long> {

    //comprobará si existe un cliente con ese documento.
    // Los nombres de los atributos deben coincidir con los que escribimos en las entidades.
    boolean existsByDocumento(String documento);
}