package com.github.hashcodejbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1")
@SpringBootApplication
public class HashcodeJbeApplication {
	public static void main(String[] args) {
		SpringApplication.run(HashcodeJbeApplication.class, args);
	}

	@GetMapping(path = "version")
	public String getVersion() {
		return "0.0.1";
	}
}
