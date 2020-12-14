package com.orange.poc.grpc.validator.domain.nif;

import reactor.core.publisher.Flux;

public interface NifValidatorIface {

    Flux<Boolean> validate(String nif);
}
