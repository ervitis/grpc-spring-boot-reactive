package com.orange.poc.grpc.validator.input.rest.v1.model;

import lombok.Data;

@Data
public class NifResponse {

    public NifResponse(Boolean isValid) {
        this.isValid = isValid;
    }

    private Boolean isValid;
}
