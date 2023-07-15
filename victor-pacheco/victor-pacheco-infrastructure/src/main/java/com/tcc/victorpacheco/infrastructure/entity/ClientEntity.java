package com.tcc.victorpacheco.infrastructure.entity;

import com.tcc.victorpacheco.domain.constant.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@NoArgsConstructor
@ToString
@Entity(name = "CLIENT")
public class ClientEntity {
    @EmbeddedId
    private ClientId id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
