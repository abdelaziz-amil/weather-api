package com.ubo.weather.repository.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.inject.Named;
import org.springframework.beans.factory.annotation.Value;

@Named
public class OpenWeatherInterceptor implements RequestInterceptor {

  @Value("${api.rest.open-weather.token}")
  private String token;

  @Override
  public void apply(RequestTemplate requestTemplate) {
    requestTemplate.query("appid", token);
  }
}
