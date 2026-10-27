package com.autorent.infrastructure.adapter.output.persistence.adapter;

import com.autorent.application.port.output.AlquilerRepositoryPort;
import com.autorent.domain.model.Alquiler;
import com.autorent.infrastructure.adapter.output.persistence.entity.AlquilerEntity;
import com.autorent.infrastructure.adapter.output.persistence.repository.AlquilerJpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public class AlquilerPersistenceAdapter implements AlquilerRepositoryPort {

    private final AlquilerJpaRepository alquilerJpaRepository;

    public AlquilerPersistenceAdapter(
            AlquilerJpaRepository alquilerJpaRepository) {
        this.alquilerJpaRepository = alquilerJpaRepository;
    }

    @Override
    public Alquiler guardar(Alquiler alquiler) {
        AlquilerEntity entidad = convertirAEntidad(alquiler);

        AlquilerEntity entidadGuardada =
                alquilerJpaRepository.save(entidad);

        return convertirADominio(entidadGuardada);
    }

    @Override
    public Optional<Alquiler> buscarPorId(Long id) {
        return alquilerJpaRepository.findById(id)
                .map(this::convertirADominio);
    }

    private AlquilerEntity convertirAEntidad(Alquiler alquiler) {
        return new AlquilerEntity(
                alquiler.getId(),
                alquiler.getClienteId(),
                alquiler.getAutoId(),
                alquiler.getFechaInicio(),
                alquiler.getFechaFin(),
                alquiler.getCantidadDias(),
                alquiler.getValorTotal(),
                alquiler.getEstado()
        );
    }

    private Alquiler convertirADominio(AlquilerEntity entidad) {
        return new Alquiler(
                entidad.getId(),
                entidad.getClienteId(),
                entidad.getAutoId(),
                entidad.getFechaInicio(),
                entidad.getFechaFin(),
                entidad.getCantidadDias(),
                entidad.getValorTotal(),
                entidad.getEstado()
        );
    }
}

//Así, al consultar un alquiler, conservamos sus datos sin calcular nuevamente el total ni cambiar el estado del auto.
//El constructor que recibe Cliente, Auto, días y fecha se utiliza en AlquilerService para crear un alquiler nuevo.