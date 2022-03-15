package com.trendyol.linkconverter.config;

import com.trendyol.linkconverter.service.LinkConverterService;
import com.trendyol.linkconverter.service.impl.LinkConverterServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LinkConverterConfiguration {
  @Bean
  public LinkConverterService linkConverterService() {
    return new LinkConverterServiceImpl();
  }
}
