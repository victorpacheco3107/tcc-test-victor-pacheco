package com.tcc.victorpacheco.domain.service;

import com.tcc.victorpacheco.domain.Client;
import com.tcc.victorpacheco.domain.constant.Gender;
import com.tcc.victorpacheco.domain.constant.IdentificationType;
import com.tcc.victorpacheco.domain.constant.Operation;
import com.tcc.victorpacheco.domain.exception.ClientAlreadyExistException;
import com.tcc.victorpacheco.domain.exception.ClientNotFoundException;
import com.tcc.victorpacheco.domain.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class DomainClientServiceTest {

    private ClientService subject;

    private ClientRepository clientRepository;


    @BeforeEach
    public void setUp() {
        this.clientRepository = Mockito.mock(ClientRepository.class);
        this.subject = new DomainClientService(this.clientRepository);
    }

    @Test
    public void WhenFindAClient_ThatNotExist_ShouldReturnEmptyOptional(){

        IdentificationType identificationType = IdentificationType.CC;
        Long identification = 1140822923l;

        Client client = Client.builder().identificationType(identificationType).identification(identification).build();

        when(clientRepository.executeProcedure(Operation.SEARCH, client)).thenReturn(Optional.empty());

        Optional<Client> clientSearched = subject.findClientByIdentificationTypeAndIdentification(identificationType, identification);

        assertNotNull(clientSearched);
        assertEquals(clientSearched.isPresent(), false);

    }

    @Test
    public void WhenFindAClient_ThatExist_ShouldReturnOptionalWithClientInfo(){

        IdentificationType identificationType = IdentificationType.CC;
        Long identification = 1140822923l;
        String name = "VICTOR";
        Gender gender = Gender.M;

        Client client = Client.builder().identificationType(identificationType).identification(identification).build();

        Client clientExpected = Client.builder()
            .identificationType(identificationType).identification(identification)
            .name(name).gender(gender).build();

        when(clientRepository.executeProcedure(Operation.SEARCH, client)).thenReturn(Optional.of(clientExpected));

        Optional<Client> clientSearched = subject.findClientByIdentificationTypeAndIdentification(identificationType, identification);

        assertNotNull(clientSearched);
        assertEquals(clientSearched.isPresent(), true);
        assertEquals(clientSearched.get().getIdentification(), clientExpected.getIdentification());
        assertEquals(clientSearched.get().getIdentificationType(), clientExpected.getIdentificationType());
        assertEquals(clientSearched.get().getName(), clientExpected.getName());
        assertEquals(clientSearched.get().getGender(), clientExpected.getGender());

    }

    @Test
    public void WhenCreateAClient_ThatIdentificationAlreadyExist_ShouldThrowClientAlreadyExistException(){

        IdentificationType identificationType = IdentificationType.CC;
        Long identification = 1140822923l;
        String name = "VICTOR";
        Gender gender = Gender.M;

        Client clientExpected = Client.builder()
            .identificationType(identificationType).identification(identification)
            .name(name).gender(gender).build();

        when(clientRepository.executeProcedure(Operation.SEARCH, clientExpected)).thenReturn(Optional.of(clientExpected));

        assertThrows(
            ClientAlreadyExistException.class, () -> subject.createClient(clientExpected)
        );

    }

    @Test
    public void WhenCreateAClient_ThatIdentificationNotExist_ShouldSaveItOnDB(){

        IdentificationType identificationType = IdentificationType.CC;
        Long identification = 1140822923l;
        String name = "VICTOR";
        Gender gender = Gender.M;

        Client clientExpected = Client.builder()
            .identificationType(identificationType).identification(identification)
            .name(name).gender(gender).build();

        when(clientRepository.executeProcedure(Operation.SEARCH, clientExpected)).thenReturn(Optional.empty());
        when(clientRepository.executeProcedure(Operation.CREATE, clientExpected)).thenReturn(Optional.of(clientExpected));

        Optional<Client> clientCreatedOpt = subject.createClient(clientExpected);

        assertNotNull(clientCreatedOpt);
        assertEquals(clientCreatedOpt.isPresent(), true);
        assertEquals(clientCreatedOpt.get().getIdentification(), clientExpected.getIdentification());
        assertEquals(clientCreatedOpt.get().getIdentificationType(), clientExpected.getIdentificationType());
        assertEquals(clientCreatedOpt.get().getName(), clientExpected.getName());
        assertEquals(clientCreatedOpt.get().getGender(), clientExpected.getGender());

    }

    @Test
    public void WhenUpdateAClient_ThatIdentificationNotExist_ShouldThrowClientNotFoundException(){

        IdentificationType identificationType = IdentificationType.CC;
        Long identification = 1140822923l;
        String name = "VICTOR";
        Gender gender = Gender.M;

        Client clientExpected = Client.builder()
            .identificationType(identificationType).identification(identification)
            .name(name).gender(gender).build();

        when(clientRepository.executeProcedure(Operation.SEARCH, clientExpected)).thenReturn(Optional.empty());

        assertThrows(
            ClientNotFoundException.class, () -> subject.updateClient(clientExpected)
        );

    }

    @Test
    public void WhenUpdateAClient_ThatIdentificationExist_ShouldUpdateItOnDB(){

        IdentificationType identificationType = IdentificationType.CC;
        Long identification = 1140822923l;
        String name = "VICTOR";
        Gender gender = Gender.M;

        Client clientExpected = Client.builder()
                .identificationType(identificationType).identification(identification)
                .name(name).gender(gender).build();

        when(clientRepository.executeProcedure(Operation.SEARCH, clientExpected)).thenReturn(Optional.of(clientExpected));
        when(clientRepository.executeProcedure(Operation.UPDATE, clientExpected)).thenReturn(Optional.of(clientExpected));

        Optional<Client> clientUpdatedOpt = subject.updateClient(clientExpected);

        assertNotNull(clientUpdatedOpt);
        assertEquals(clientUpdatedOpt.isPresent(), true);
        assertEquals(clientUpdatedOpt.get().getIdentification(), clientExpected.getIdentification());
        assertEquals(clientUpdatedOpt.get().getIdentificationType(), clientExpected.getIdentificationType());
        assertEquals(clientUpdatedOpt.get().getName(), clientExpected.getName());
        assertEquals(clientUpdatedOpt.get().getGender(), clientExpected.getGender());

    }

    @Test
    public void WhenDeleteAClient_ThatIdentificationNotExist_ShouldThrowClientNotFoundException(){

        IdentificationType identificationType = IdentificationType.CC;
        Long identification = 1140822923l;

        Client clientExpected = Client.builder()
            .identificationType(identificationType).identification(identification)
            .build();

        when(clientRepository.executeProcedure(Operation.SEARCH, clientExpected)).thenReturn(Optional.empty());

        assertThrows(
            ClientNotFoundException.class, () -> subject.deleteClient(identificationType, identification)
        );

    }



    @Test
    public void WhenDeleteAClient_ThatIdentificationExist_ShouldDeleteItOnDB(){

        IdentificationType identificationType = IdentificationType.CC;
        Long identification = 1140822923l;
        String name = "VICTOR";
        Gender gender = Gender.M;

        Client client = Client.builder()
                .identificationType(identificationType).identification(identification)
                .build();

        Client clientExpected = Client.builder()
                .identificationType(identificationType).identification(identification)
                .name(name).gender(gender).build();

        when(clientRepository.executeProcedure(Operation.SEARCH, client)).thenReturn(Optional.of(clientExpected));
        when(clientRepository.executeProcedure(Operation.DELETE, client)).thenReturn(Optional.empty());

        subject.deleteClient(identificationType, identification);

    }

}
