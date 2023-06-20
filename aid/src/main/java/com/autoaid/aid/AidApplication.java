package com.autoaid.aid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AidApplication {

	public static void main(String[] args) {
		SpringApplication.run(AidApplication.class, args);
	}

}
