package com.ubo.weather.controller;

import com.ubo.weather.business.CityBusiness;
import com.ubo.weather.mapper.CityMapper;
import dto.weatherapi.City;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import java.awt.*;

@Controller
@Path("/city")
public class CityController {

  @Inject
  CityBusiness cityBusiness;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createCity(City city) {
    cityBusiness.createCity(CityMapper.toEntity(city));
    return Response.ok(city).build();
  }
}
