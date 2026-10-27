package com.autorent.application.port.input;

import com.autorent.domain.model.Alquiler;

public interface CrearAlquilerUseCase {

    Alquiler crear(Long clienteId, Long autoId, int cantidadDias);
}
//el que lee esto es gay?
//si claro, pero no importa, lo importante es que sepas que este es un puerto de entrada,
// que expresa una operación que alguien puede solicitar a la aplicación, en este caso, crear un alquiler.
//Puerto	Qué expresa	Ejemplo
//Entrada	Una operación que alguien puede solicitar a la aplicación.	Crear un alquiler.
//Salida	Una operación externa que la aplicación necesita para hacer su trabajo.	Buscar un auto o guardar un alquiler.