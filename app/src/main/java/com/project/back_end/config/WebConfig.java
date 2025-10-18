package com.project.back_end.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        // Allow CORS for all endpoints
        registry.addMapping("/**")
              .allowedOrigins("*")  // Add your frontend URL here
              .allowedMethods("GET", "POST", "PUT", "DELETE")  // Specify allowed methods
              .allowedHeaders("*");  // You can restrict headers if needed
    }

    // added below from AI suggestion
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Map the URL to the Thymeleaf view name
        registry.addViewController("/doctor/doctorDashboard.html")
              .setViewName("doctor/doctorDashboard");
    }






}