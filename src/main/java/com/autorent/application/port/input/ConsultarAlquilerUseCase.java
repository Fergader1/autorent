package com.autorent.application.port.input;

import com.autorent.domain.model.Alquiler;

public interface ConsultarAlquilerUseCase {

    Alquiler consultarPorId(Long id);
}

//Puerto	Qué expresa	Ejemplo
//Entrada	Una operación que alguien puede solicitar a la aplicación.	Crear un alquiler.
//Salida	Una operación externa que la aplicación necesita para hacer su trabajo.	Buscar un auto o guardar un alquiler.

