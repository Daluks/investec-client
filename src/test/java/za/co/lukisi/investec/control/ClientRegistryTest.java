package za.co.lukisi.investec.control;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.co.lukisi.investec.entity.Client;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ClientRegistryTest {

    ClientRegistry clientRegistry = new ClientRegistry();

    @Before
    public void loadClients() {
        clientRegistry.addClient(new Client("Lukisi", "Motsoeneng", "8409205652089", "75 Valley Road",
                "0821234567"));
    }

    @Test
    public void getAllClients() {

        loadClients();
        List<Client> allClients = clientRegistry.getAllClients();
        assertEquals(1, allClients.size());
    }

    @Test
    public void getClientByIdNumber() {
        Client clientByIdNumber = clientRegistry.getClientByIdNumber("8409205652089");
        assertEquals("8409205652089",clientByIdNumber.getIdNumber());
    }

    @Test
    public void addClient() {
        Client client = buildClient();
        clientRegistry.addClient(client);
        assertEquals(client, clientRegistry.getClientByIdNumber(client.getIdNumber()));
    }

    @Test
    public void updateClient() {
        Client updateClient = buildUpdateClient();
        clientRegistry.updateClient(updateClient);
        assertEquals(updateClient, clientRegistry.getClientByIdNumber("8609205652089"));
    }

    @Test
    public void deleteClient() {
    }

    @Test
    public void getClientByFirstName() {
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