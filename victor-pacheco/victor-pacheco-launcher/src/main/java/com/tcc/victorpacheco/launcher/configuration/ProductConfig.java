package com.tcc.victorpacheco.launcher.configuration;

import com.tcc.victorpacheco.domain.service.ClientService;
import com.tcc.victorpacheco.domain.service.DomainClientService;
import com.tcc.victorpacheco.infrastructure.repository.ClientJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductConfig {

    @Bean
    public ClientService productService(ClientJpaRepository productHttpRepository){
        return new DomainClientService(productHttpRepository);
    }

}
