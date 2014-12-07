package com.timsmeet.persistance.spring;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.timsmeet.persistance.repositories")
@ComponentScan(basePackages = {"com.timsmeet.persistance.model"})
@Import({PersistenceDevConfig.class, PersistenceTestConfig.class})
@EnableTransactionManagement
public class PersistenceJPAConfig {

  @Autowired 
  DataSource dataSource;
  @Autowired
  JpaVendorAdapter jpaVendorAdapter;
  @Autowired
  Properties additionalHibernateProperties;

  public PersistenceJPAConfig() {
    super();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

    // vendorAdapter.set
    em.setJpaVendorAdapter(jpaVendorAdapter);
    em.setJpaProperties(additionalHibernateProperties);
    
    em.setPackagesToScan("com.timsmeet.persistance.model");
    
    em.setDataSource(dataSource);

    return em;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    final JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

    return transactionManager;
  }

  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }


}