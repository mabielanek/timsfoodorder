package com.timsmeet.services.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.timsmeet.persistance.spring.PersistenceJPAConfig;

@Configuration
@ComponentScan(basePackages = {"com.timsmeet.services"})
@EnableTransactionManagement
@Import({PersistenceJPAConfig.class})
public class ServicesConfig {

}
