package com.vitavehiculum.web;

import com.vitavehiculum.web.initializer.UserInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

	private static UserInitializer userInitializer;

	public WebApplication(UserInitializer userInitializer){
		WebApplication.userInitializer = userInitializer;
	}

	public static void main(String[] args) {

		SpringApplication.run(WebApplication.class, args);

		userInitializer.init();

	}

}
