package com.biomed.bm_access_layer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@PropertySource("classpath:application.yml")
public class BmAccessLayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BmAccessLayerApplication.class, args);
	}

}
