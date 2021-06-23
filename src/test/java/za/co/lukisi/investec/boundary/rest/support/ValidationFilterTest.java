package za.co.lukisi.investec.boundary.rest.support;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import za.co.lukisi.investec.control.ClientRegistry;
import za.co.lukisi.investec.entity.Client;

import javax.ws.rs.container.ContainerRequestContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ValidationFilterTest {

    @InjectMocks
    ValidationFilter validationFilter;

    @Mock
    ClientRegistry clientRegistry;

    private final String INPUT_DATA = "{\n" +
            "\t\"firstName\":\"Max\",\n" +
            "\t\"lastName\":\"\",\n" +
            "\t\"phoneNumber\":\"082082\",\n" +
            "\t\"idNumber\":\"8409205652080\",\n" +
            "\t\"physicalAddress\":\"75 That Street, City\"\n" +
            "}";

    @Test
    public void when_invalid_request_is_sent() throws IOException {

        ContainerRequestContext requestContext = Mockito.mock(ContainerRequestContext.class);

        InputStream inputStream = new ByteArrayInputStream(INPUT_DATA.getBytes(StandardCharsets.UTF_8));
        when(requestContext.getEntityStream()).thenReturn(inputStream);

        validationFilter.filter(requestContext);
        Assert.assertEquals(Arrays.asList("Invalid Id Number supplied"), validationFilter.errorMessages);

    }

    @Test
    public void when_phone_number_exists_return_error() throws IOException {
        ContainerRequestContext requestContext = Mockito.mock(ContainerRequestContext.class);

        InputStream inputStream = new ByteArrayInputStream(INPUT_DATA.getBytes(StandardCharsets.UTF_8));
        when(requestContext.getEntityStream()).thenReturn(inputStream);

        when(clientRegistry.getClientByPhoneNumber("082082")).thenReturn(new Client());

        validationFilter.filter(requestContext);
        Assert.assertTrue(validationFilter.errorMessages.stream()
            .filter(msg -> msg.equals("Client with this number already exists")).findFirst().isPresent());
    }



}