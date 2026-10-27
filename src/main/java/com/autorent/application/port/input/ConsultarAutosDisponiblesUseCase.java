package com.autorent.application.port.input;

import com.autorent.domain.model.Auto;

import java.util.List;

public interface ConsultarAutosDisponiblesUseCase {

    List<Auto> consultarDisponibles();
}

//Puerto	Qué expresa	Ejemplo
//Entrada	Una operación que alguien puede solicitar a la aplicación.	Crear un alquiler.
//Salida	Una operación externa que la aplicación necesita para hacer su trabajo.	Buscar un auto o guardar un alquiler.