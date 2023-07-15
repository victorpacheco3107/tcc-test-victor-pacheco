package com.tcc.victorpacheco.domain.service;

import com.tcc.victorpacheco.domain.Client;
import com.tcc.victorpacheco.domain.constant.IdentificationType;
import com.tcc.victorpacheco.domain.constant.Operation;
import com.tcc.victorpacheco.domain.exception.ClientAlreadyExistException;
import com.tcc.victorpacheco.domain.exception.ClientNotFoundException;
import com.tcc.victorpacheco.domain.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of the interface {@link  ClientService  ClientService}
 * @author <a href="mailto:victorpacheco3107@gmail.com">Victor Uriel Pacheco Castro</a>
 * @since 1.0
 */
public class DomainClientService implements ClientService {

    /**
     * Repository for database operations.
     */
    private final ClientRepository clientRepository;

    public DomainClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * @see ClientService#findClientByIdentificationTypeAndIdentification(IdentificationType, Long)
     */
    @Override
    public Optional<Client> findClientByIdentificationTypeAndIdentification(IdentificationType identificationType, Long identification) {
        return clientRepository.executeProcedure(
            Operation.SEARCH,
            Client.builder().identificationType(identificationType).identification(identification).build()
        );
    }

    /**
     * @see ClientService#createClient(Client)
     */
    @Override
    public Optional<Client> createClient(Client client) {
        Optional<Client> clientSearchedOpt = clientRepository.executeProcedure(Operation.SEARCH, client);
        if(clientSearchedOpt.isPresent()){
            throw new ClientAlreadyExistException(client);
        }
        return clientRepository.executeProcedure(Operation.CREATE, client);
    }

    /**
     * @see ClientService#createClient(Client)
     */
    @Override
    public Optional<Client> updateClient(Client client) {
        Optional<Client> clientSearchedOpt = clientRepository.executeProcedure(Operation.SEARCH, client);
        if(clientSearchedOpt.isEmpty()){
            throw new ClientNotFoundException(client);
        }
        return clientRepository.executeProcedure(Operation.UPDATE, client);
    }

    /**
     * @see ClientService#deleteClient(IdentificationType, Long)
     */
    @Override
    public void deleteClient(IdentificationType identificationType, Long identification) {
        Client client = Client.builder().identificationType(identificationType).identification(identification).build();
        Optional<Client> clientSearchedOpt = clientRepository.executeProcedure(Operation.SEARCH, client);
        if(clientSearchedOpt.isEmpty()){
            throw new ClientNotFoundException(client);
        }
        clientRepository.executeProcedure(Operation.DELETE, client);
    }

    /**
     * @see ClientService#findAllClients()
     */
    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAllClients();
    }

}
