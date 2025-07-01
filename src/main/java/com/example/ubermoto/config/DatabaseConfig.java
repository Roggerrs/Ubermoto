//package com.example.ubermoto.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DatabaseConfig {
//
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//}