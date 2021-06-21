package za.co.lukisi.investec.control;

import za.co.lukisi.investec.entity.Client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ApplicationScoped
public class ClientRegistry {

    private final Map<String, Client> clientsCache = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {

        clientsCache.put("8409205652089",
                new Client("Lukisi", "Motsoeneng", "8409205652089", "75 Valley Road",
                        "0821234567"));
        System.out.println(" DATA LOADED");
    }

    public List<Client> getAllClients() {
        return clientsCache.values().stream().collect(Collectors.toList());
    }

    public Client getClientByIdNumber(String idNumber)
    {
        return clientsCache.get(idNumber);
    }

    public Client addClient(Client client) {
        return clientsCache.putIfAbsent(client.getIdNumber(), client);
    }

    public Client updateClient(Client client) {
        System.out.println(client.getFirstName());
        return clientsCache.put(client.getIdNumber(), client);
    }

    public Client deleteClient(String idNumber) {
        return clientsCache.remove(idNumber);
    }

    public List<Client> getClientByFirstName(final String firstName){

        return clientsCache.values().stream()
                .filter(client -> client.getFirstName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());


    }
}
