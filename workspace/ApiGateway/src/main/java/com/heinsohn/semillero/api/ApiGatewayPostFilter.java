package com.heinsohn.semillero.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * Esta clase permite filtrar las peticiones que llegan al api, despues de enrutar las peticiones
 * 
 * @author Danner Uribe
 *
 */
public class ApiGatewayPostFilter implements GlobalFilter {

	/**
	 * Atributo que permite registrar logs en el log principal de spring boot
	 */
	final Logger logger = LoggerFactory.getLogger(ApiGatewayPostFilter.class);
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			logger.info("Se ejecuta el Filtrado Global para las peticiones!!");
			
			
		}));
	}

}
