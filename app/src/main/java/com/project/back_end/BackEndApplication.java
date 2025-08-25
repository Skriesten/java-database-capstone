package com.project.back_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
//@SpringBootApplication
@ComponentScan("com.project.back_end")
public class BackEndApplication {

	public static void main(String[] args) {

        SpringApplication.run(BackEndApplication.class, args);
	}

}
