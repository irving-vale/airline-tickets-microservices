package com.irving.cursoKubernetes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcFlightsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcFlightsApplication.class, args);
	}

}
