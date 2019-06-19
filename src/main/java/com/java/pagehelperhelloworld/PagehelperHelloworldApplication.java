package com.java.pagehelperhelloworld;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.java.pagehelperhelloworld.mapper"})
public class PagehelperHelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagehelperHelloworldApplication.class, args);
	}

}
