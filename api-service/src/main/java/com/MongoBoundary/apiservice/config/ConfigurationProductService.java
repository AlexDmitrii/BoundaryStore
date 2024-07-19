package com.MongoBoundary.apiservice.config;

import com.netflix.discovery.shared.transport.jersey3.Jersey3TransportClientFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationProductService {

    @Bean
    public Jersey3TransportClientFactories jersey3TransportClientFactoriesApi() {
        return new Jersey3TransportClientFactories();
    }

}