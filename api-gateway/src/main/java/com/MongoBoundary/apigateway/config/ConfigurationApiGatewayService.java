package com.MongoBoundary.apigateway.config;

import com.netflix.discovery.shared.transport.jersey3.Jersey3TransportClientFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationApiGatewayService {

    @Bean
    public Jersey3TransportClientFactories jersey3TransportClientFactoriesApiGateway() {
        return new Jersey3TransportClientFactories();
    }

}