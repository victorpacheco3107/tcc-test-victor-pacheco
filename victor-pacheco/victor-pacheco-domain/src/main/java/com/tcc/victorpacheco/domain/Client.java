package com.tcc.victorpacheco.domain;

import com.tcc.victorpacheco.domain.constant.Gender;
import com.tcc.victorpacheco.domain.constant.IdentificationType;
import lombok.Builder;
import lombok.Data;

/**
 * Entity that represents a Client.
 * @author <a href="mailto:victorpacheco3107@gmail.com">Victor Uriel Pacheco Castro</a>
 * @since 1.0
 */
@Data
@Builder
public class Client {
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
