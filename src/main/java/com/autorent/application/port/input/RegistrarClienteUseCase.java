package com.autorent.application.port.input;

import com.autorent.domain.model.Cliente;

public interface RegistrarClienteUseCase {

    Cliente registrar(String nombre, String documento,
                      String telefono, String email);
}

//Puerto	Qué expresa	Ejemplo
//Entrada	Una operación que alguien puede solicitar a la aplicación.	Crear un alquiler.
//Salida	Una operación externa que la aplicación necesita para hacer su trabajo.	Buscar un auto o guardar un alquiler.
