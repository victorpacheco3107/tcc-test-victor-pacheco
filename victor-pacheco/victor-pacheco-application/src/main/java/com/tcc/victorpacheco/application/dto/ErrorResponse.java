package com.tcc.victorpacheco.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private List<String> errors;

    public static ErrorResponse buildSingleError(String error){
        return new ErrorResponse(Collections.singletonList(error));
    }

}
