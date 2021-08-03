package com.project.buku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BukuApplication /* extends SpringBootServletInitializer */  {
	
//	@Bean
//    public ServletRegistrationBean<ServletContainer> jerseyServlet() {
//        ServletRegistrationBean<ServletContainer> registration = new ServletRegistrationBean<ServletContainer>(new ServletContainer(), "/api/*");
//        // our rest resources will be available in the path /rest/*
//        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfiguration.class.getName());
//        return registration;
//    }

	public static void main(String[] args) {
		SpringApplication.run(BukuApplication.class, args);
	}

}
