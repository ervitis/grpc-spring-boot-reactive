package com.orange.poc.grpc.validator.input.rest.v1;

import com.orange.poc.grpc.validator.input.rest.v1.model.NifResponse;
import reactor.core.publisher.Flux;


public interface ValidatorApiRestIface {

    Flux<NifResponse> validateNif(String document);
}