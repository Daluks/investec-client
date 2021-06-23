package za.co.lukisi.investec.boundary.rest.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import za.co.lukisi.investec.control.ClientRegistry;
import za.co.lukisi.investec.entity.Client;
import za.co.lukisi.investec.util.IdNumberValidator;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Provider
@ValidatedEntity
@RequestScoped
public class ValidationFilter implements ContainerRequestFilter {

  List<String> errorMessages = new ArrayList<>();

  @Inject
  ClientRegistry clientRegistry;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {

    InputStream entityStream = requestContext.getEntityStream();
    String inputData = IOUtils.toString(entityStream, StandardCharsets.UTF_8);
    requestContext.setEntityStream(new ByteArrayInputStream(inputData.getBytes(StandardCharsets.UTF_8)));

    try {

      Client client = new ObjectMapper().readValue(inputData, Client.class);
      validateIdNumber(client);
      validateExistingPhoneNumber(client);

      if (!errorMessages.isEmpty()) {
        requestContext.abortWith(Response.status(Response.Status.OK).entity(ErrorResponse.builder()
            .errorMessages(errorMessages)
            .request(client.getIdNumber()).build()).build());
      }
    } catch (IOException e) {
      requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).entity(ErrorResponse.builder()
          .errorMessages(Arrays.asList("Invalid Payload")).build()).build());
      e.printStackTrace();
    }

  }

  protected void validateIdNumber(Client client) {
    if (!new IdNumberValidator().isValidIdNumber(client.getIdNumber())) {
      errorMessages.add("Invalid Id Number supplied");
    }
  }

  protected void validateExistingPhoneNumber(Client client) {

    if (!Objects.isNull(clientRegistry.getClientByPhoneNumber(client.getPhoneNumber()))) {
      errorMessages.add("Client with this number already exists");
    }

  }
}
