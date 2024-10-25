package com.ubo.weather.filtres;

import dto.weatherapi.WeatherError;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@AuthenticationRequired
public class AuthenticationRequiredImpl implements ContainerRequestFilter {
  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    if (containerRequestContext.getHeaderString("Authentication") == null) {
      WeatherError error = new WeatherError();
      error.setMessage("vous n'êtes pas autorisé");
      error.setCode("401");
      containerRequestContext
              .abortWith(Response.status(Response.Status.UNAUTHORIZED)
              .entity(error)
              .build());
    }
  }
}
