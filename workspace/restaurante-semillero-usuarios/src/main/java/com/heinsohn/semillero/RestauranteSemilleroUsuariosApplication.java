package com.heinsohn.semillero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.heinsohn.semillero.service.UsuariosService;
import com.heinsohn.semillero.service.UsuariosServiceImpl;

@SpringBootApplication
@EnableDiscoveryClient
public class RestauranteSemilleroUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteSemilleroUsuariosApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
