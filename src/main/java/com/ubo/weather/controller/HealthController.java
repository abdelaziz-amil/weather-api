package com.ubo.weather.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Controller;

@Controller
@Path("/check")
public class HealthController {

  @GET
  public Response getHealthCheck() {
    return Response.ok("it's OK, I'm up !").build();
  }
}
