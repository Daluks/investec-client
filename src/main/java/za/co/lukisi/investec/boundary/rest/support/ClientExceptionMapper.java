package za.co.lukisi.investec.boundary.rest.support;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Provider
public class ClientExceptionMapper implements ExceptionMapper<ConstraintViolationException> {


    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(prepareErrorMessage(e.getConstraintViolations()))
                .build();
    }

    private ErrorResponse prepareErrorMessage(Set<ConstraintViolation<?>> constraintViolations){
        List<String> messages = new ArrayList<>();
        constraintViolations.stream().forEach(violation -> messages.add(violation.getPropertyPath() +" "+ violation.getMessage()));

        return ErrorResponse.builder().errorMessages(messages).build();
    }
}
