package com.ticketservice.dao.springboot.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@ComponentScan("com.ticketservice.dao.springboot")
public class SpringConfigForHM12 {


    @Bean
    @ConditionalOnProperty(prefix = "conditionalBeans", name = "value", havingValue = "true")
    public String thisIsMyFirstConditionalBean() {
        return "this is my first conditional bean which I get to know from AndersenLab";
    }

}
