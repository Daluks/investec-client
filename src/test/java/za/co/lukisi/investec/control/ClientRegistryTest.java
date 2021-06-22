package za.co.lukisi.investec.control;

import org.junit.Before;
import org.junit.Test;
import za.co.lukisi.investec.entity.Client;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class ClientRegistryTest {

    ClientRegistry clientRegistry = new ClientRegistry();

    Validator validator;

    @Before
    public void setUp() {
        clientRegistry.addClient(new Client("Lukisi", "Motsoeneng", "8409205652089", "75 Valley Road",
                "0821234567"));
        clientRegistry.addClient(new Client("Joel", "Test", "8409205652088", "75 Valley Road",
                "0821234567"));

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void return_2_clients_when_getAll_isCalled() {

        List<Client> allClients = clientRegistry.getAllClients();
        assertEquals(2, allClients.size());
    }

    @Test
    public void return_client_by_queried_idNumber() {
        Client clientByIdNumber = clientRegistry.getClientByIdNumber("8409205652089");
        assertEquals("8409205652089",clientByIdNumber.getIdNumber());
    }

    @Test
    public void when_client_is_added_must_be_return_by_the_IdNumber() {
        Client client = buildClient();
        clientRegistry.addClient(client);
        assertEquals(client, clientRegistry.getClientByIdNumber(client.getIdNumber()));
    }

    @Test
    public void when_client_is_queried_by_first_name_client_with_exact_name_is_returned() {

        clientRegistry.addClient(buildClient());
        List<Client> clients = clientRegistry.getClientByFirstName("Luis");
        List<String> jamesS = clients.stream().map(client -> client.getFirstName()).collect(Collectors.toList());
        assertTrue(clients.size() > 0);
        assertFalse(jamesS.removeIf(name -> !name.equals("Luis")));

    }

    @Test
    public void when_existing_client_name_is_updated_client_return_with_new_name() {
        Client updateClient = buildUpdateClient();
        clientRegistry.updateClient(updateClient);
        assertEquals("James", clientRegistry.getClientByIdNumber("8609205652089").getFirstName());
    }

    @Test
    public void when_client_is_deleted_the_query_by_idNumber_should_return_null() {
        assertNotNull(clientRegistry.getClientByIdNumber("8409205652088"));
        clientRegistry.deleteClient("8409205652088");
        assertEquals(null, clientRegistry.getClientByIdNumber("8409205652088"));
    }

    @Test
    public void return_firstname_violation_when_null(){
        Client client = Client.builder().lastName("Luks").idNumber("840920").mobileNumber("10111").physicalAddress("That one").build();
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        ConstraintViolation<Client> violation = violations.stream().findFirst().get();
        assertEquals("firstName may not be null", violation.getPropertyPath() +" "+ violation.getMessage());
    }

    @Test
    public void return_idnumber_violation_when_null(){
        Client client = Client.builder().lastName("Luks").firstName("Joe").mobileNumber("10111").physicalAddress("That one").build();
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        ConstraintViolation<Client> violation = violations.stream().findFirst().get();
        assertEquals("idNumber may not be null", violation.getPropertyPath() +" "+ violation.getMessage());
    }

    @Test
    public void return_lastName_violation_when_null(){
        Client client = Client.builder().idNumber("840920").firstName("Joe").mobileNumber("10111").physicalAddress("That one").build();
        Set<ConstraintViolation<Client>> violations = validator.validate(client);
        ConstraintViolation<Client> violation = violations.stream().findFirst().get();
        assertEquals("lastName may not be null", violation.getPropertyPath() +" "+ violation.getMessage());
    }

    private Client buildClient() {
        return Client.builder()
                .idNumber("8609205652089")
                .firstName("Luis")
                .lastName("Jones")
                .mobileNumber("0829067999")
                .physicalAddress("That place")
                .build();
    }

    private Client buildUpdateClient() {
        return Client.builder()
                .idNumber("8609205652089")
                .firstName("James")
                .lastName("Jones")
                .mobileNumber("0829067999")
                .physicalAddress("That place")
                .build();
    }
}