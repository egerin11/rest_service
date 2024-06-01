package com.example.laba.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Mapper config.
 */
@Configuration
public class MapperConfig {
  /**
   * Model mapper model mapper.
   *
   * @return the model mapper
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
