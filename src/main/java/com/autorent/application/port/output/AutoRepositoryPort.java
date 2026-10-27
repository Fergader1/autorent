package com.autorent.application.port.output;

import com.autorent.domain.model.Auto;

import java.util.List;
import java.util.Optional;

public interface AutoRepositoryPort {

    Auto guardar(Auto auto);

    Optional<Auto> buscarPorId(Long id);

    List<Auto> buscarDisponibles();
}

//Estos métodos permitirán guardar un auto, buscarlo por su identificador y consultar los disponibles.