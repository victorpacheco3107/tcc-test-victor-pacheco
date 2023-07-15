package com.tcc.victorpacheco.application.dto;

import com.tcc.victorpacheco.domain.constant.Gender;
import com.tcc.victorpacheco.domain.constant.IdentificationType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientResponse {
    /**
     * Client identification type.
     */
    private IdentificationType identificationType;
    /**
     * Client identification.
     */
    private Long identification;
    /**
     * Client name.
     */
    private String name;
    /**
     * Client gender.
     */
    private Gender gender;
}
