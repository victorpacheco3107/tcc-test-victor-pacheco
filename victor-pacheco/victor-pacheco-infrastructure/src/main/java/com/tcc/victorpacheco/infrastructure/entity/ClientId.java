package com.tcc.victorpacheco.infrastructure.entity;

import com.tcc.victorpacheco.domain.constant.IdentificationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class ClientId implements Serializable {
    @Enumerated(EnumType.STRING)
    private IdentificationType identificationType;
    private Long identification;
}
