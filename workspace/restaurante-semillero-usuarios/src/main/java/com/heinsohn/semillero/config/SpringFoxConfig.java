package com.heinsohn.semillero.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build().apiInfo(getInfoApi());
	}
	
	private ApiInfo getInfoApi() {
		return new ApiInfo("API Servicios para los usuarios", 
						   "Este api permite usar el crud para los usuarios de la aplicación.", 
						   "1.0.0", "https://heinsohn.com.co", 
						   new Contact("Danner Uribe", "https://heinsohn.com.co", "duribe@heinsohn.com.co"),
						   "License",
						   "https://heinsohn.com.co/license",
						   Collections.emptyList());
	}
	
}
