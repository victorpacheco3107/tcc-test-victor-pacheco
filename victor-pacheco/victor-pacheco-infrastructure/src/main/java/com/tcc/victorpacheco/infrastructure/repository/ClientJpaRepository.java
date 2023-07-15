package com.tcc.victorpacheco.infrastructure.repository;

import com.tcc.victorpacheco.domain.Client;
import com.tcc.victorpacheco.domain.constant.Gender;
import com.tcc.victorpacheco.domain.constant.IdentificationType;
import com.tcc.victorpacheco.domain.constant.Operation;
import com.tcc.victorpacheco.domain.repository.ClientRepository;
import com.tcc.victorpacheco.infrastructure.entity.ClientEntity;
import com.tcc.victorpacheco.infrastructure.entity.ClientId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
@Transactional
public interface ClientJpaRepository extends ClientRepository, JpaRepository<ClientEntity, ClientId> {

    int IDENTIFICATION_TYPE_INDEX = 0;
    int IDENTIFICATION_INDEX = 1;
    int NAME_INDEX = 2;
    int GENDER_INDEX = 3;

    @Procedure(procedureName = "CLIENT_PKG.CLIENT_CRUD", outputParameterName = "client_out", refCursor = true)
    Object executeProcedureClientCrud(
        @Param("operation_in") Integer operation,
        @Param("identification_type_in") String identificationType,
        @Param("identification_in") Long identification,
        @Param("name_in") String name,
        @Param("gender_in") String gender
    );


    @Override
    default Optional<Client> executeProcedure(Operation operation, Client client){
        Object clientResult = executeProcedureClientCrud(
            operation.getOperationId(),
            client.getIdentificationType().name(),
            client.getIdentification(),
            client.getName(),
            client.getGender() == null ? null : client.getGender().name()
        );

        if (clientResult == null){
            return Optional.empty();
        }

        Object[] clientColumnData = (Object[]) clientResult;

        return Optional.of(
            Client.builder()
                .identificationType(IdentificationType.valueOf((String) clientColumnData[IDENTIFICATION_TYPE_INDEX]))
                .identification(((BigDecimal) clientColumnData[IDENTIFICATION_INDEX]).longValue())
                .name((String) clientColumnData[NAME_INDEX])
                .gender(Gender.valueOf((String) clientColumnData[GENDER_INDEX]))
                .build()
        );
    }

}
