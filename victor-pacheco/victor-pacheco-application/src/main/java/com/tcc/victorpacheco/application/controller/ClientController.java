package com.tcc.victorpacheco.application.controller;

import com.tcc.victorpacheco.application.dto.ClientResponse;
import com.tcc.victorpacheco.application.dto.CreateClientRequest;
import com.tcc.victorpacheco.application.dto.UpdateClientRequest;
import com.tcc.victorpacheco.application.mapper.ClientMapper;
import com.tcc.victorpacheco.domain.Client;
import com.tcc.victorpacheco.domain.constant.IdentificationType;
import com.tcc.victorpacheco.domain.exception.ClientNotFoundException;
import com.tcc.victorpacheco.domain.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientMapper clientMapper;
    private final ClientService clientService;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @GetMapping("/{identification}")
    public ResponseEntity<?> findClient(
        @PathVariable
        @NotNull(message = "identification is required")
        @Min(value = 100, message = "identification's min length is 3")
        @Max(value = 9999999999l, message = "identification's max length is 10")
        Long identification,
        @RequestParam
        @NotBlank(message = "identificationType is required")
        @Pattern(regexp = "CC|CE|NIT", message = "identificationType must be CC, CE or NIT")
        String identificationType){

        Optional<Client> clientSearchedOpt = clientService.findClientByIdentificationTypeAndIdentification(
            IdentificationType.valueOf(identificationType),
            identification
        );

        if(clientSearchedOpt.isEmpty()){
            throw new ClientNotFoundException(
                Client.builder().identificationType(IdentificationType.valueOf(identificationType)).identification(identification).build()
            );
        }

        return ResponseEntity.ok(clientMapper.clientToResponse(clientSearchedOpt.get()));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> createClient(@RequestBody @Valid CreateClientRequest createClientRequest){

        Optional<Client> clientSearchedOpt = clientService.createClient(clientMapper.createRequestToClient(createClientRequest));

        return clientSearchedOpt.isEmpty() ?
            ResponseEntity.internalServerError().build() :
            ResponseEntity.status(HttpStatus.CREATED).body(clientMapper.clientToResponse(clientSearchedOpt.get()));
    }

    @PutMapping("/{identification}")
    public ResponseEntity<ClientResponse> updateClient(
        @PathVariable
        @NotNull(message = "identification is required")
        @Min(value = 100, message = "identification's min length is 3")
        @Max(value = 9999999999l, message = "identification's max length is 10")
        Long identification,
        @RequestParam
        @NotBlank(message = "identificationType is required")
        @Pattern(regexp = "CC|CE|NIT", message = "identificationType must be CC, CE or NIT")
        String identificationType,
        @RequestBody @Valid UpdateClientRequest updateClientRequest){

        Client clientToUpdate = clientMapper.updateRequestToClient(updateClientRequest);
        clientToUpdate.setIdentificationType(IdentificationType.valueOf(identificationType));
        clientToUpdate.setIdentification(identification);

        Optional<Client> clientUpdatedOpt = clientService.updateClient(clientToUpdate);

        return clientUpdatedOpt.isEmpty() ?
            ResponseEntity.internalServerError().build() :
            ResponseEntity.ok(clientMapper.clientToResponse(clientUpdatedOpt.get()));
    }

    @DeleteMapping("/{identification}")
    public ResponseEntity<ClientResponse> deleteClient(
        @PathVariable
        @NotNull(message = "identification is required")
        @Min(value = 100, message = "identification's min length is 3")
        @Max(value = 9999999999l, message = "identification's max length is 10")
        Long identification,
        @RequestParam
        @NotBlank(message = "identificationType is required")
        @Pattern(regexp = "CC|CE|NIT", message = "identificationType must be CC, CE or NIT")
        String identificationType){

        clientService.deleteClient(IdentificationType.valueOf(identificationType), identification);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAllClient(){
        return ResponseEntity.ok(clientMapper.clientToResponse(clientService.findAllClients()));
    }


}
