package com.autorent.application.port.output;

import com.autorent.domain.model.Alquiler;

import java.util.Optional;

public interface AlquilerRepositoryPort {

    Alquiler guardar(Alquiler alquiler);

    Optional<Alquiler> buscarPorId(Long id);
}

//Usaremos guardar() tanto para registrar un alquiler nuevo como para actualizar uno existente al finalizarlo.

//Auto guardar(Auto auto);
//Significa:
//- Recibe un objeto Auto.
//- La operación se llama guardar.
//- Devuelve un objeto Auto, que podrá incluir el identificador asignado al guardarlo.
//No tiene un cuerpo con { ... } porque aquí declaramos el contrato. El adaptador contendrá la implementación.

//Los otros tipos de retorno significan:
//Tipo	             Qué devuelve?
//Optional<Auto>	 Un resultado que puede contener un auto o estar vacío si no existe.
//List<Auto>	     Una lista de autos; puede estar vacía.
//boolean	         true o false.