package com.tcc.victorpacheco.domain.repository;

import com.tcc.victorpacheco.domain.Client;
import com.tcc.victorpacheco.domain.constant.Operation;

import java.util.Optional;

/**
 * Repository for query operations on the {@link  Client  Client} entity.
 * @author <a href="mailto:victorpacheco3107@gmail.com">Victor Uriel Pacheco Castro</a>
 * @since 1.0
 */
public interface ClientRepository {

    /**
     * Execute the procedure CLIENT_PKG.CLIENT_CRUD
     * @param operation Operation to perform. 0 for search, 1 for insert, 2 for update and 3 for delete.
     * @param client Information to pass on to procedure.
     * @return Client with information returned by procedure.
     */
    Optional<Client> executeProcedure(Operation operation, Client client);

}
