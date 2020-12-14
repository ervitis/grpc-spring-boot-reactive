package com.orange.poc.grpc.validator.domain.nif.impl;

import com.orange.poc.grpc.validator.domain.nif.NifValidatorIface;
import com.orange.poc.validator.NifRequest;
import com.orange.poc.validator.ValidatorServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class NifValidator implements NifValidatorIface {

    @GrpcClient(value = "validator-service")
    private ValidatorServiceGrpc.ValidatorServiceBlockingStub validatorServiceGrpc;

    public Flux<Boolean> validate(String nif) {
        NifRequest nifRequest = NifRequest.newBuilder().setNifDocument(nif).build();

        return Flux.create(sink -> {
            this.validatorServiceGrpc.checkNifValidityStream(nifRequest).
                    forEachRemaining(response -> sink.next(response.getIsValid()));
            sink.complete();
        });
    }
}
