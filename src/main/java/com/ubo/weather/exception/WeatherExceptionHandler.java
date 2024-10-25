package com.ubo.weather.exception;

import dto.weatherapi.WeatherError;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.springframework.http.HttpStatus;


@Provider
public class WeatherExceptionHandler implements ExceptionMapper<WeatherException> {
  @Override
  public Response toResponse(WeatherException e) {
    WeatherError error = new WeatherError();
    error.setCode(e.getCode());
    error.setMessage(e.getMessage());
    return Response.status(HttpStatus.BAD_REQUEST.value()).entity(error).build();
  }
}
