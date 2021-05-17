package com.heinsohn.semillero.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import io.jsonwebtoken.Jwts;
import reactor.core.publisher.Mono;

/**
 * Esta clase permite controlar las peticiones que se realizan a
 * todos los micro servicios registrados al Api-Gateway
 * 
 * @author Danner Uribe
 *
 */
@Component
public class AuthorizationHeaderFilter extends AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config> {

	@Autowired
	Environment env;
	
	/**
	 * Metodo constructor, se pasa por debfecto la clase estadica de configuracion
	 */
	public AuthorizationHeaderFilter() {
		super(Config.class);
	}
	
	public static class Config {
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		// Usamos una expresion Lambda para obtener y validar el token de cada peticion
		return (exchange, chain) -> {
			
			ServerHttpRequest request = exchange.getRequest();
			
			if(request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				return onError(exchange, "No authorization header", HttpStatus.UNAUTHORIZED);
			}
			
			String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			
			String jwt = authorizationHeader.replace("Bearer", "");
			
			if(!isJwtValid(jwt))
				return onError(exchange, "JWT is not valid!", HttpStatus.UNAUTHORIZED);
			
			return chain.filter(exchange);
		};
	}
	
	/**
	 * Este metodo permite armar la respuesta a cada peticion que pasa por el ApyGateway
	 * 
	 * @param exchange
	 * @param error
	 * @param httpStatus
	 * @return Mono<Void>
	 */
	private Mono<Void> onError(ServerWebExchange exchange, String error, HttpStatus httpStatus) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		return response.setComplete();
	}
	
	/**
	 * Este metodo permite verificar que se ecuentre un token de autenticacion en la peticion
	 * 
	 * @param jwt
	 * @return boolean
	 */
	private boolean isJwtValid(String jwt) {
		String subject = "";
		
		try {
			subject = Jwts.parser()
							.setSigningKey(env.getProperty("token.secret"))
							.parseClaimsJws(jwt)
							.getBody()
							.getSubject();
		}
		catch(Exception e) {
			
		}
		
		if(null == subject || subject.isEmpty())
			return false;
		
		return true;
	}
	
}
