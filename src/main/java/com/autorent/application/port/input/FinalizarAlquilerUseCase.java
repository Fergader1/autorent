package com.autorent.application.port.input;

import com.autorent.domain.model.Alquiler;

public interface FinalizarAlquilerUseCase {

    Alquiler finalizar(Long id);
}

//¿Cuál es la diferencia con las interfaces anteriores?
//Puerto	Qué expresa	Ejemplo
//Entrada	Una operación que alguien puede solicitar a la aplicación.	Crear un alquiler.
//Salida	Una operación externa que la aplicación necesita para hacer su trabajo.	Buscar un auto o guardar un alquiler.