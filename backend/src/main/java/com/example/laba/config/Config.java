package com.example.laba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The type Config.
 */
@Configuration
public class Config {

  /**
   * Rest template rest template.
   *
   * @return the rest template
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
