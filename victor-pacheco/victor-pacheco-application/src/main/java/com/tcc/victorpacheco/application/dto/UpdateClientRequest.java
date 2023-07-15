package com.tcc.victorpacheco.application.dto;

import com.tcc.victorpacheco.domain.constant.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UpdateClientRequest {

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
