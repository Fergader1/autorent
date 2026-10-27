package com.autorent.infrastructure.config;

import com.autorent.application.port.output.AlquilerRepositoryPort;
import com.autorent.application.port.output.AutoRepositoryPort;
import com.autorent.application.port.output.ClienteRepositoryPort;
import com.autorent.application.service.AlquilerService;
import com.autorent.application.service.AutoService;
import com.autorent.application.service.ClienteService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AutoService autoService(
            AutoRepositoryPort autoRepository) {
        return new AutoService(autoRepository);
    }

    @Bean
    public ClienteService clienteService(
            ClienteRepositoryPort clienteRepository) {
        return new ClienteService(clienteRepository);
    }

    @Bean
    public AlquilerTransactionalDecorator alquilerTransactionalDecorator(
            ClienteRepositoryPort clienteRepository,
            AutoRepositoryPort autoRepository,
            AlquilerRepositoryPort alquilerRepository) {

        AlquilerService alquilerService = new AlquilerService(
                clienteRepository,
                autoRepository,
                alquilerRepository
        );

        return new AlquilerTransactionalDecorator(alquilerService);
    }
}