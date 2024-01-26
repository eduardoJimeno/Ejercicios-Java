package com.bosonit.formacion.apigateway;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDiscoveryClient
public class GetawayConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                // Configurar rutas a tus microservicios
                .route("cliente-service", r -> r.path("/cliente/**").uri("lb://cliente-service"))
                .route("viaje-service", r -> r.path("/viaje/**").uri("lb://viaje-service"))
                // Agregar más rutas según tus microservicios
                .build();
    }
}
