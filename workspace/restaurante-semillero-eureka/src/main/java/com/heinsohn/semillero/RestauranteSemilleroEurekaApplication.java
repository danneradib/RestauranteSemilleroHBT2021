package com.heinsohn.semillero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RestauranteSemilleroEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestauranteSemilleroEurekaApplication.class, args);
	}

}
