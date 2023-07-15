package com.tcc.victorpacheco.launcher.configuration;

import com.tcc.victorpacheco.domain.service.ClientService;
import com.tcc.victorpacheco.domain.service.DomainClientService;
import com.tcc.victorpacheco.infrastructure.repository.ClientJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    public ClientService clientService(ClientJpaRepository clientJpaRepository){
        return new DomainClientService(clientJpaRepository);
    }

}
