package com.ubo.weather.exception;


public class WeatherException extends Exception {
  private String code;

  public WeatherException(String code, String message) {
    super(message);
    this.code = code;
  }
  public String getCode() {
    return code;
  }
}
