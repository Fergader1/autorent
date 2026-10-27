package com.autorent.infrastructure.adapter.output.persistence.repository;

import com.autorent.domain.model.EstadoAuto;
import com.autorent.infrastructure.adapter.output.persistence.entity.AutoEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutoJpaRepository extends JpaRepository<AutoEntity, Long> {

    //buscará autos cuyo atributo estado coincida con el valor recibido.
    // Más adelante le pasaremos EstadoAuto.DISPONIBLE.
    List<AutoEntity> findByEstado(EstadoAuto estado);
}