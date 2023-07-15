package com.tcc.victorpacheco.application.mapper;

import com.tcc.victorpacheco.application.dto.CreateClientRequest;
import com.tcc.victorpacheco.application.dto.ClientResponse;
import com.tcc.victorpacheco.application.dto.UpdateClientRequest;
import com.tcc.victorpacheco.domain.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientResponse clientToResponse(Client client);
    Client createRequestToClient(CreateClientRequest request);
    Client updateRequestToClient(UpdateClientRequest request);
}
