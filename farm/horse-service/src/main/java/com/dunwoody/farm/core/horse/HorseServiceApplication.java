package com.dunwoody.farm.core.horse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dunwoody")
public class HorseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HorseServiceApplication.class, args);
	}

}
