package com.timsmeet.persistance.spring;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.google.common.base.Preconditions;

@Configuration
@Profile("test")
@PropertySource({"classpath:persistence-h2sql.properties" })
public class PersistenceTestConfig {

  @Autowired
  private Environment env;

  @Bean
  public DataSource dataSource() {
    final DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
    dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
    dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.user")));
    dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.pass")));

    return dataSource;
  }
  
  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setDatabase(Database.H2);
    //jpaVendorAdapter.setGenerateDdl(true);
    return jpaVendorAdapter;
  }

  @Bean
  public Properties additionalHibernateProperties() {
    return new Properties() {

      private static final long serialVersionUID = -6641535886154967843L;
      
      {
        setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

        // setProperty("hibernate.globally_quoted_identifiers", "true");
        // note: necessary in launchpad-storage, but causing problems here
      }
    };
  }

}
