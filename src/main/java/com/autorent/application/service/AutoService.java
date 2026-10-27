package com.autorent.application.service;

import com.autorent.application.port.input.ConsultarAutoUseCase;
import com.autorent.application.port.input.ConsultarAutosDisponiblesUseCase;
import com.autorent.application.port.input.RegistrarAutoUseCase;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.domain.exception.AutoNoEncontradoException;
import com.autorent.domain.model.Auto;

import java.math.BigDecimal;
import java.util.List;


//implements: “esta clase se compromete a cumplir esta interfaz”.
//@Override: “verifica que este método corresponde a uno de los que estoy implementando o sobrescribiendo”.


//¿Qué hace cada parte?
//implements indica que la clase cumple los contratos de las interfaces. Por eso contiene sus tres métodos:
// registrar(), consultarPorId() y consultarDisponibles().

public class AutoService implements RegistrarAutoUseCase,
        ConsultarAutoUseCase,
        ConsultarAutosDisponiblesUseCase {

    private final AutoRepositoryPort autoRepository;

    public AutoService(AutoRepositoryPort autoRepository) {
        this.autoRepository = autoRepository;
    }


    //@Override indica que el método implementa una operación declarada en una interfaz. No es una anotación de Spring: pertenece a Java.


    @Override
    public Auto registrar(String placa, String marca, String modelo,
                          Integer anio, BigDecimal precioPorDia) {

        Auto auto = new Auto(
                placa,
                marca,
                modelo,
                anio,
                precioPorDia
        );

        return autoRepository.guardar(auto);
    }

    //significa: “si el Optional contiene un auto, devuélvelo; si está vacío, lanza esta excepción”.
    //El () -> es una lambda: aquí proporciona la acción que Java ejecutará para crear la excepción solamente si no encuentra el auto.
    //Nuestro recorrido ya empieza a tomar forma:
    @Override
    public Auto consultarPorId(Long id) {
        return autoRepository.buscarPorId(id)
                .orElseThrow(() -> new AutoNoEncontradoException(id));
    }
    //que hace la aplicación cuando alguien solicita consultar un auto por su identificador:
    //1. Llama al adaptador para buscar el auto en la base de datos.
    //2. Si lo encuentra, lo devuelve.
    //3. Si no lo encuentra, lanza una excepción AutoNoEncontradoException con el identificador que buscaba.

    @Override
    public List<Auto> consultarDisponibles() {
        return autoRepository.buscarDisponibles();
    }
}