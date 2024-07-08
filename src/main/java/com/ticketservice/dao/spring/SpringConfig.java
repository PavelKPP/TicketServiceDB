package com.ticketservice.dao.spring;


import com.ticketservice.dao.service.TicketDAOService;
import com.ticketservice.dao.service.UserDAOService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.ticketservice.dao")
public class SpringConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/my_ticket_service_db");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("password");

        return driverManagerDataSource;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager platformTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }


    @Bean
    public TicketDAOService ticketDAOService(DataSource dataSource) {
        return new TicketDAOService(dataSource);
    }

    @Bean
    public UserDAOService userDAOService(DataSource dataSource) {
        return new UserDAOService(dataSource);
    }

}
