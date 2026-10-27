package com.autorent.infrastructure.config;

import com.autorent.application.port.input.ConsultarAlquilerUseCase;
import com.autorent.application.port.input.CrearAlquilerUseCase;
import com.autorent.application.port.input.FinalizarAlquilerUseCase;
import com.autorent.application.service.AlquilerService;
import com.autorent.domain.model.Alquiler;

import org.springframework.transaction.annotation.Transactional;

public class AlquilerTransactionalDecorator
        implements CrearAlquilerUseCase,
        ConsultarAlquilerUseCase,
        FinalizarAlquilerUseCase {

    private final AlquilerService alquilerService;

    public AlquilerTransactionalDecorator(
            AlquilerService alquilerService) {
        this.alquilerService = alquilerService;
    }

    @Override
    @Transactional
    public Alquiler crear(Long clienteId, Long autoId, int cantidadDias) {
        return alquilerService.crear(clienteId, autoId, cantidadDias);
    }

    //Esto indica que la operación está destinada a leer datos.
    //Así mantenemos las anotaciones de Spring en infraestructura,
    // mientras AlquilerService continúa siendo Java independiente de Spring.
    @Override
    @Transactional(readOnly = true)
    public Alquiler consultarPorId(Long id) {
        return alquilerService.consultarPorId(id);
    }

    @Override
    @Transactional
    public Alquiler finalizar(Long id) {
        return alquilerService.finalizar(id);
    }
}
                                      //¿Qué es este “decorador”?

         //Es una clase que envuelve al servicio para añadirle un comportamiento: en este caso, la transacción. Las reglas y operaciones siguen en AlquilerService.
        //Cuando llamemos a este componente a través de Spring, @Transactional permitirá confirmar los cambios juntos o deshacerlos si se propaga una excepción como nuestras RuntimeException. Documentación oficial de Spring
        //En la consulta usamos: