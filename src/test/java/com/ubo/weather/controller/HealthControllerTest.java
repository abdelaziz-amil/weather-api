package com.ubo.weather.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class HealthControllerTest extends ControllerTest {


  @Test
  public void testHealth() {
    RestAssured.given()
      .get("/check")
      .then()
      .statusCode(HttpStatus.OK.value());
  }
}
