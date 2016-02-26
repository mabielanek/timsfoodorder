package com.timsmeet.rest.controllers;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.springtestdbunit.ExpectTableOverwritenTestExecutionListener;
import com.timsmeet.rest.controllers.spring.RestServicesConfig;
import com.timsmeet.spring.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {RestServicesConfig.class, TestConfig.class})
@ActiveProfiles("test")
@TestExecutionListeners({ 
  DependencyInjectionTestExecutionListener.class,
  DirtiesContextTestExecutionListener.class,
  TransactionalTestExecutionListener.class,
  ServletTestExecutionListener.class,
  ExpectTableOverwritenTestExecutionListener.class })
@Ignore
public class BaseControllerTest {

  @Resource
  protected WebApplicationContext webApplicationContext;

  protected MockMvc mockMvc;

  @Before
  public void setUp() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

}
