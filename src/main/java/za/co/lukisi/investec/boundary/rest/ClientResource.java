package za.co.lukisi.investec.boundary.rest;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import za.co.lukisi.investec.boundary.rest.support.ErrorResponse;
import za.co.lukisi.investec.boundary.rest.support.ValidatedEntity;
import za.co.lukisi.investec.control.ClientRegistry;
import za.co.lukisi.investec.entity.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Objects;

@Path("/client")
@ApplicationScoped
@OpenAPIDefinition(info = @Info(title = "Client endpoint", version = "1.0"))
public class ClientResource {

    @Inject
    ClientRegistry clientRegistry;

    @GET
    @Path("/all")
    public Response getAllClients() {
        return Response
                .status(Response.Status.OK)
                .entity(clientRegistry.getAllClients())
                .build();
    }

    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Client by firstname",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    ref = "Client"))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "No Client found")
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("firstname/{firstName}")
    public Response getClientByFirstname(@PathParam("firstName") String firstname) {
        return Response
                .status(Response.Status.OK)
                .entity(clientRegistry.getClientByFirstName(firstname))
                .build();
    }

    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Client by phoneNumber",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    ref = "Client"))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "No Client found")
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("phoneNumber/{phoneNumber}")
    public Response getClientByPhoneNumber(@PathParam("phoneNumber") String phoneNumber) {
        return Response
                .status(Response.Status.OK)
                .entity(clientRegistry.getClientByIdNumber(phoneNumber))
                .build();
    }

    @APIResponses(value = {
            @APIResponse(
                    responseCode = "200",
                    description = "Client by ID number",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(
                                    ref = "Client"))
            ),
            @APIResponse(
                    responseCode = "404",
                    description = "No Client found")
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("idNumber/{idNumber}")
    public Response getClientByIdNumber(@PathParam("idNumber") String idNumber) {

        Client clientByIdNumber = clientRegistry.getClientByIdNumber(idNumber);

        if (Objects.isNull(clientByIdNumber)) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(ErrorResponse.builder()
                            .errorMessages(Arrays.asList("No Data found"))
                            .request(idNumber).build()).build();
        } else {
            return Response
                    .status(Response.Status.OK)
                    .entity(clientRegistry.getClientByIdNumber(idNumber))
                    .build();
        }

    }

    @ValidatedEntity
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addClient(@Valid final Client client) {
        return Response
                .status(Response.Status.OK)
                .entity(clientRegistry.addClient(client))
                .build();
    }

    @ValidatedEntity
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateClient(@Valid final Client client) {
        return Response
                .status(Response.Status.OK)
                .entity(clientRegistry.updateClient(client))
                .build();
    }

    @DELETE
    @Path("/{idNumber}")
    public Response deleteClient(@PathParam("idNumber") String idNumber) {
        return Response
                .status(Response.Status.OK)
                .entity(clientRegistry.deleteClient(idNumber))
                .build();
    }


}
