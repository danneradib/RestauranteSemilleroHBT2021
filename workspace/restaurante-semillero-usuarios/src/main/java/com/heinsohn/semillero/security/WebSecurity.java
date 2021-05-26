package com.heinsohn.semillero.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.heinsohn.semillero.enums.RolesEnum;
import com.heinsohn.semillero.service.UsuariosService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private Environment environment;

	/**
	 * Atributo que permite cifrar y/o descifrar las contraseñas
	 */
	private BCryptPasswordEncoder bCryptPassworEncoder;

	/**
	 * Atributo que contiene los metodos de la logica de negocio
	 */
	private UsuariosService usuariosService;

	/**
	 * Metodo contructor donde se inicializan los atributos de validacion
	 * 
	 * @param environment
	 * @param bCryptPassworEncoder
	 * @param usuariosService
	 */
	@Autowired
	public WebSecurity(Environment environment, BCryptPasswordEncoder bCryptPassworEncoder,
			UsuariosService usuariosService) {
		this.environment = environment;
		this.bCryptPassworEncoder = bCryptPassworEncoder;
		this.usuariosService = usuariosService;
	}

	/**
	 * Este metodo permite realizar las configuraciones personalizadas para nuestra
	 * aplicacion
	 * 
	 * @param HttpSecurity
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"))
		.antMatchers(environment.getProperty("login.url.path")).permitAll()		
		.antMatchers("/menu/**").permitAll()
				.antMatchers("/productos/**")
				.hasAnyAuthority(RolesEnum.ADMINISTRADOR.toString(), RolesEnum.CHEFF.toString(),
						RolesEnum.CLIENTE.toString())
				.antMatchers("/ordenes/**").hasAuthority(RolesEnum.CHEFF.toString()).and()
				.addFilter(getAuthenticationFilter());

		http.headers().frameOptions().disable();
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(usuariosService, environment,
				authenticationManager());
		authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
		return authenticationFilter;
	}

	protected void configure(AuthenticationManagerBuilder mgrBuild) throws Exception {
		mgrBuild.userDetailsService(usuariosService).passwordEncoder(bCryptPassworEncoder);
	}

}
