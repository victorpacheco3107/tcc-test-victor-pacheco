package com.tcc.victorpacheco.domain.service;

import com.tcc.victorpacheco.domain.Client;
import com.tcc.victorpacheco.domain.constant.IdentificationType;

import java.util.List;
import java.util.Optional;

/**
 * Class with business logic for {@link  Client  Client} entity.
 * @author <a href="mailto:victorpacheco3107@gmail.com">Victor Uriel Pacheco Castro</a>
 * @since 1.0
 */
public interface ClientService {

    /**
     * Find a client given the {@link  Client  Client}'s id.
     * @param identificationType {@link  Client#identificationType Client}'s identification type to find.
     * @param identification {@link  Client#identification Client}'s identification to find.
     * @return Optional object with {@link  Client  Client} information if it exists,
     * otherwise an optional empty is returned.
     */
    Optional<Client> findClientByIdentificationTypeAndIdentification(IdentificationType identificationType, Long identification);

    /**
     * Create a {@link  Client  Client}
     * @param client {@link  Client  Client} to create.
     * @return If the client is created successfully an optional object is returned with client information, ig an error
     * occurs an optional empty is returned.
     */
    Optional<Client> createClient(Client client);

    /**
     * Update a {@link  Client  Client}
     * @param client {@link  Client  Client} to create.
     * @return If the client is created successfully an optional object is returned with client information, ig an error
     * occurs an optional empty is returned. Only the name and gender can to be updated.
     */
    Optional<Client> updateClient(Client client);

    /**
     * Delete a {@link  Client  Client}
     * @param identificationType {@link  Client#identificationType Client}'s identification type to delete.
     * @param identification {@link  Client#identification Client}'s identification to delete.
     */
    void deleteClient(IdentificationType identificationType, Long identification);

    /**
     * Find all clients.
     * @return All clients.
     */
    List<Client> findAllClients();

}
