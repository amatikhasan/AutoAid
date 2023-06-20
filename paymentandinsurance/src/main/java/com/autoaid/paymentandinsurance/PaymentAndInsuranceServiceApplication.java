package com.autoaid.paymentandinsurance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PaymentAndInsuranceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentAndInsuranceServiceApplication.class, args);
	}

}
