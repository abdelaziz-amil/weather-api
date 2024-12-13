package com.ubo.weather.controller;

import com.ubo.weather.repository.client.OpenWeatherClient;
import dto.openmeteoapi.Main;
import dto.openmeteoapi.Model200;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;

public class WeatherControllerTest extends ControllerTest {

  @Mock
  OpenWeatherClient openWeatherClient;
      @Test
      public void testWeather() {

        var cityName = "Brest";
        var model200 = new Model200();
        var main = new Main();
        main.setHumidity(20);
        main.setTemp(BigDecimal.ONE);
        model200.setMain(main);
        model200.setName(cityName);
        Mockito.when(openWeatherClient.getWeather(any())).thenReturn(model200);
          RestAssured.given()
                  .queryParam("cityname=Brest")
            .get("/weather")
            .then()
            .statusCode(HttpStatus.OK.value());
      }
}
