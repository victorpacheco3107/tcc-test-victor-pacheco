package com.tcc.victorpacheco.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class CreateClientRequest {

    /**
     * Client identification type.
     */
    @NotBlank(message = "identificationType is required")
    @Pattern(regexp = "CC|CE|NIT", message = "identificationType must be CC, CE or NIT")
    private String identificationType;

    /**
     * Client identification.
     */
    @NotNull(message = "identification is required")
    @Min(value = 100, message = "identification's min length is 3")
    @Max(value = 9999999999l, message = "identification's max length is 10")
    private Long identification;

    /**
     * Client name.
     */
    @NotBlank(message = "name is required")
    @Size(min = 3, max = 100, message = "name's length must be between 3 and 100")
    private String name;

    /**
     * Client gender.
     */
    @NotBlank(message = "gender is required")
    @Pattern(regexp = "M|F", message = "gender must be M or F")
    private String gender;

}
