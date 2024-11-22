package com.ubo.weather.controller;

import com.ubo.weather.business.CityBusiness;
import com.ubo.weather.business.WeatherBusiness;
import com.ubo.weather.mapper.WeatherMapper;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
@Path("/weather")
public class WeatherController {
  @Inject
  private WeatherBusiness weatherBusiness;

  @Inject
  private CityBusiness cityBusiness;
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getWeatherByCityName(@QueryParam("cityName") String cityName) {
//    ArrayList<String> name = new ArrayList<>();
//    name.add(cityName);
//    var isExistingCity = cityBusiness.getListCityByName(name);
//    if (isExistingCity.isEmpty()) {
//      return Response.status(Response.Status.NOT_FOUND).build();
//    }
    var res = WeatherMapper.toDto(weatherBusiness.getWeatherByCityName(cityName));
    return Response.ok(res).build();
  }

}
