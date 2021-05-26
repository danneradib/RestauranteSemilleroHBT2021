package com.heinsohn.semillero.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heinsohn.semillero.dto.LoginDTO;
import com.heinsohn.semillero.dto.UsuarioDTO;
import com.heinsohn.semillero.service.UsuariosService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private UsuariosService usuariosService;
	
	private Environment environment;
	
	public AuthenticationFilter(UsuariosService usuariosService, Environment environment, AuthenticationManager authenticationManager) {
		this.usuariosService = usuariosService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
	}

	/**
	 * Este metodo permite validar las peticiones a la aplicacion para los usuarios
	 * validando el login y password en la peticion
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * 
	 * @return Authentication
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		try {
			// Obtenemos las credenciales con el objeto LoginDTO del request
			LoginDTO creds = new ObjectMapper().readValue(req.getInputStream(), LoginDTO.class);
			
			// Creamos una instancia del objeto Autenticacion con las crdenciales del usuario
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getLogin(),
							creds.getPassword(),
							new ArrayList<>()));
		}
		catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}
	
	/**
	 * Este metodo permite realizar la verificacion de la infromacion del usurio
	 * para cada peticion que realiza en la aplicacion, posterior a su autenticacion
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param FilterChain
	 * @param Authentication
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest req, 
			HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		// Se obtiene el login del usuario
		String login = ((User) auth.getPrincipal()).getUsername();
		
		// Se consulta la infromacion del usuario de la BD
		UsuarioDTO usuarioDTO = usuariosService.getUsetDateilsByUserLogin(login);
		
		Map<String, Object> claims = new HashMap<>();
		
		claims.put("Roles", usuarioDTO.getRoles().toArray().toString());
		
		// Se configura el tokem con el login, una fecha de expiracion y un token secreto
		String token = Jwts.builder()
				.setClaims(claims)
				.setSubject(String.valueOf(usuarioDTO.getIdUsuario()))
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
				.compact();
		
		// Se responde el token el las cabeceras de la respuesta (Headers)
		res.addHeader("token", token);
		
		// Se responde el id Publico del usaurio en las cabeceras de la respuesta (Headers)
		res.addHeader("idUsuario", usuarioDTO.getIdPublico());
	}
	
}
