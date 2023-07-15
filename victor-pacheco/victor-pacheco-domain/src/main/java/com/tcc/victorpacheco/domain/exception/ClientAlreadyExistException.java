package com.tcc.victorpacheco.domain.exception;

import com.tcc.victorpacheco.domain.Client;

public class ClientAlreadyExistException extends TccException{
    private Client client;

    public ClientAlreadyExistException(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
