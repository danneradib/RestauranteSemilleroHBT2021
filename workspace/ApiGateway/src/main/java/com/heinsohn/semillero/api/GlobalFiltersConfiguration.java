package com.heinsohn.semillero.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.publisher.Mono;

@Configuration
public class GlobalFiltersConfiguration {

	/**
	 * Atributo que permite registrar logs en el log principal de spring boot
	 */
	final Logger logger = LoggerFactory.getLogger(GlobalFiltersConfiguration.class);
	
	@Bean
	public GlobalFilter secondPreFilter() {
		return (exchange, chain) -> {
			
			logger.info("Pre-Filtro Global ejecutado!!");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				
				logger.info("El segundo Post-Filtro Global ejecutado!!");
			
			}));
			
		};
	}
	
	@Bean
	public GlobalFilter thirdPreFilter() {
		return (exchange, chain) -> {
			
			logger.info("Pre-Filtro Global ejecutado!!");
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				
				logger.info("El tercer Post-Filtro Global ejecutado!!");
			
			}));
			
		};
	}
	
}
