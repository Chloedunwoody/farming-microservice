package com.dunwoody.farm.core.barn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dunwoody")
public class BarnServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarnServiceApplication.class, args);
	}

}
