package com.irving.cursoKubernetes.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("msvc-flights-route", r -> r
                        .path("/api/v1/fly/**", "/api/v1/tickets/**")
                        .filters(f -> f
                                .addRequestHeader("X-Gateway-Request", "Gateway-Header-Success"))
                        .uri("lb://MSVC-FLIGHTS"))
                .route("msvc-users-route", r -> r
                        .path("/api/v1/customer/**")
                        .uri("lb://MSVC-USERS"))
                .build();
    }

}
