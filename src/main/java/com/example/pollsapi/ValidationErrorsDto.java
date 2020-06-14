package com.example.pollsapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsDto {

    @JsonProperty("field_errors")
    private final List<FieldErrorDto> fieldErrors = new ArrayList<>();

    public void addFieldError(String field, String message) {
        FieldErrorDto fieldError = new FieldErrorDto(field, message);
        this.fieldErrors.add(fieldError);
    }

    public List<FieldErrorDto> getFieldErrors() {
        return fieldErrors;
    }
}
