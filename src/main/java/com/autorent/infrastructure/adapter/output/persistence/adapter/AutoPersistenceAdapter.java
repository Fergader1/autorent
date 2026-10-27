package com.autorent.infrastructure.adapter.output.persistence.adapter;

import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.domain.model.Auto;
import com.autorent.domain.model.EstadoAuto;
import com.autorent.infrastructure.adapter.output.persistence.entity.AutoEntity;
import com.autorent.infrastructure.adapter.output.persistence.repository.AutoJpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AutoPersistenceAdapter implements AutoRepositoryPort {

    private final AutoJpaRepository autoJpaRepository;

    public AutoPersistenceAdapter(AutoJpaRepository autoJpaRepository) {
        this.autoJpaRepository = autoJpaRepository;
    }

    @Override
    public Auto guardar(Auto auto) {
        AutoEntity entidad = convertirAEntidad(auto);

        AutoEntity entidadGuardada = autoJpaRepository.save(entidad);

        return convertirADominio(entidadGuardada);
    }

    @Override
    public Optional<Auto> buscarPorId(Long id) {
        return autoJpaRepository.findById(id)
                .map(this::convertirADominio);
    }

    @Override
    public List<Auto> buscarDisponibles() {
        return autoJpaRepository.findByEstado(EstadoAuto.DISPONIBLE)
                .stream()
                .map(this::convertirADominio)
                .toList();
    }

    private AutoEntity convertirAEntidad(Auto auto) {
        return new AutoEntity(
                auto.getId(),
                auto.getPlaca(),
                auto.getMarca(),
                auto.getModelo(),
                auto.getAnio(),
                auto.getPrecioPorDia(),
                auto.getEstado()
        );
    }

    private Auto convertirADominio(AutoEntity entidad) {
        return new Auto(
                entidad.getId(),
                entidad.getPlaca(),
                entidad.getMarca(),
                entidad.getModelo(),
                entidad.getAnio(),
                entidad.getPrecioPorDia(),
                entidad.getEstado()
        );
    }
}

//ClienteService
//    ↓
//ClienteRepositoryPort.existePorDocumento(...)
//    ↓
//ClientePersistenceAdapter
//    ↓
//ClienteJpaRepository.existsByDocumento(...)
//    ↓
//H2 devuelve si existe