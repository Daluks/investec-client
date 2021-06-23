package za.co.lukisi.investec.boundary.rest;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 */
@Path("/ping")
@Singleton
public class Ping {

  @GET
  public String sayHello() {
    return "Pong";
  }
}
