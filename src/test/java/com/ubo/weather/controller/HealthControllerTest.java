package com.ubo.weather.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class HealthControllerTest extends ControllerTest {

  @Test
  public void testHealth() {
    RestAssured.given()
      .when()
      .get("/health")
      .then()
      .statusCode(200);
  }
}
