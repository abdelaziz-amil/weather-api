package com.ubo.weather.controller;

import com.ubo.weather.business.CityBusiness;
import com.ubo.weather.exception.CityAlreadyExistException;
import com.ubo.weather.exception.CityDoesntExistException;
import com.ubo.weather.mapper.CityMapper;
import dto.weatherapi.City;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Path("/city")
public class CityController {

  @Inject
  CityBusiness cityBusiness;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response createCity(City city) {
    try {
      cityBusiness.createCity(CityMapper.toEntity(city));
      return Response.ok(city).build();
    } catch (CityAlreadyExistException e) {
      throw new RuntimeException(e);
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllCities() {
    return Response.ok(cityBusiness.getAllCities()).build();
  }

  @GET
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCityByListName(@PathParam("names") List<String> names, @PathParam("ids") List<String> ids) {
    return Response.ok(CityMapper.toDto(cityBusiness.getCityByListName(names))).build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateCity(City city) {
    try {
      cityBusiness.updateCity(CityMapper.toEntity(city));
      return Response.ok(city).build();
    } catch (Exception e) {
      throw new RuntimeException("Impossible de mettre à jour les données de la ville", e);
    }
  }

  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteCityById(@PathParam("id") Long id) throws CityDoesntExistException {
    City city = CityMapper.toDto(cityBusiness.deleteCityById(id));
    return Response.ok(city).build();
  }
}
