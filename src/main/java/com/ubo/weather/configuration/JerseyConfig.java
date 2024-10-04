package com.ubo.weather.configuration;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

// Jersey est responsable de la partie restful donc la partie controller de l'application
// Pour ce faire on scan les beans du controller depuis le Jersey
@Configuration
@ApplicationPath("/api/v1")
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig() {
    packages("com.ubo.weather.controller");
  }
}
