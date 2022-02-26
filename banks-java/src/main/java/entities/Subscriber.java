package entities;

import tools.ClientException;

public class Subscriber {

    private final Client client;

    public Subscriber(Client client) throws ClientException {
        if (client == null)
            throw new ClientException("No client to create subscriber");
        this.client = client;
    }

    public Client getClient(){
        return this.client;
    }
}
