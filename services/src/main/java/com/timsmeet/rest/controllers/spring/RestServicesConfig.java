package com.timsmeet.rest.controllers.spring;

import java.util.List;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.timsmeet.services.spring.ServicesConfig;

@Configuration
@Import({ServicesConfig.class})
@ComponentScan(basePackages = {"com.timsmeet.rest.controllers"})
@EnableWebMvc
public class RestServicesConfig extends WebMvcConfigurerAdapter {

  //More configuration....

  /* Here we register the Hibernate4Module into an ObjectMapper, then set this custom-configured ObjectMapper
   * to the MessageConverter and return it to be added to the HttpMessageConverters of our application*/
  public MappingJackson2HttpMessageConverter jacksonMessageConverter(){
      MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

      ObjectMapper mapper = new ObjectMapper();
//      mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
//      mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
      
      mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

      messageConverter.setObjectMapper(mapper);
      return messageConverter;
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
      //Here we add our custom-configured HttpMessageConverter
      converters.add(jacksonMessageConverter());
      super.configureMessageConverters(converters);
  }

  public RestServicesConfig() {
    super();
  }

}