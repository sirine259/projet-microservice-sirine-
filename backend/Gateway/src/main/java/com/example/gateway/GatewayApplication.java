package com.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Route pour le service de gestion des candidats
				.route("candidat",
						r -> r.path("/candidat/**")
								.uri("lb://candidat"))
				// Route pour le service de gestion des offres d'emploi
				.route("job",
						r -> r.path("/job/**")
								.uri("lb://job"))
				// Route pour le service de gestion des candidatures
				.route("candidature",
						r -> r.path("/candidature/**")
								.uri("lb://candidature"))
				// Route pour le service de gestion des réunions
				.route("meeting",
						r -> r.path("/meeting/**")
								.uri("lb://meeting"))
				// Route pour le service de notification
				.route("notification",
						r -> r.path("/notification/**")
								.uri("lb://notification"))
				.build();
	}
}