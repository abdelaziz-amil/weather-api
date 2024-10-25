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
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
  public Response getCityByListName(@QueryParam("list-name") List<String> listNames, @QueryParam("list-id") List<String> listId) {
    List<City> response;
    if (listId != null) {
      response = cityBusiness.getListCityById(listId)
              .stream()
              .map(CityMapper::toDto)
              .collect(Collectors.toList());
    } else if (!listNames.isEmpty()) {
      response = cityBusiness.getListCityByName(listNames)
              .stream()
              .map(CityMapper::toDto)
              .collect(Collectors.toList());
    } else {
      response = Collections.emptyList();
    }

    return Response.ok(response).build();
  }

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateCity(@QueryParam("City") City city) {
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
  @RequestMapping("/{id}")
  public Response deleteCityById(@PathParam("id") Long id) throws CityDoesntExistException {
    City city = CityMapper.toDto(cityBusiness.deleteCityById(id));
    return Response.ok(city).build();
  }
}
