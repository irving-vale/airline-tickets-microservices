package com.irving.cursoKubernetes.msvc_tours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MsvcToursApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcToursApplication.class, args);
	}

}
