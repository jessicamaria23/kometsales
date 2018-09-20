package com.elcafetal.elcafetal;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.elcafetal.elcafetal.models.service.ProductService;

@SpringBootApplication
public class ElcafetalApplication {
	
	@Resource
	ProductService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(ElcafetalApplication.class, args);
	}
}