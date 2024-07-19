package com.MongoBoundary.orderservice.config;

import com.netflix.discovery.shared.transport.jersey3.Jersey3TransportClientFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationOrderService {


    @Bean
    public Jersey3TransportClientFactories jersey3TransportClientFactoriesProduct() {
        return new Jersey3TransportClientFactories();
    }
}
