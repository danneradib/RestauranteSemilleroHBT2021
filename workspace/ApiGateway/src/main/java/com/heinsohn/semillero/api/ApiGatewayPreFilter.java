package com.heinsohn.semillero.api;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class ApiGatewayPreFilter implements GlobalFilter {

	/**
	 * Atributo que permite registrar logs en el log principal de spring boot
	 */
	final Logger logger = LoggerFactory.getLogger(ApiGatewayPreFilter.class);
	
	/**
	 * Metodo que permite filtrar las peticiones que llegan al api-gateway
	 * 
	 * @param ServerWebExchange
	 * @param GatewayFilterChain
	 * 
	 * @return Mono<Void>
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		logger.info("Pre-Filtro executado....!");
		
		String requesPath = exchange.getRequest().getPath().toString();
		
		logger.info("Request path = " + requesPath);
		
		HttpHeaders headers = exchange.getRequest().getHeaders();
		
		Set<String> headerNames = headers.keySet();
		
		headerNames.forEach((headerName) -> {
			String headerValue = headers.getFirst(headerName);
			logger.info(headerName + " " + headerValue);
		});
		
		return chain.filter(exchange);
	}

}
