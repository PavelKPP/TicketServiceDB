package com.ticketservice.dao.springboot;

import com.ticketservice.dao.springboot.config.SpringConfigForHM12;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfigForHM12.class);
        Environment environment = applicationContext.getEnvironment();
    }
}
