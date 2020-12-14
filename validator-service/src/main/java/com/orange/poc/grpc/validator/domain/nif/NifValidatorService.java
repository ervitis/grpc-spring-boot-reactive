package com.orange.poc.grpc.validator.domain.nif;

import com.orange.poc.validator.NifRequest;
import com.orange.poc.validator.ValidatorResponse;
import com.orange.poc.validator.ValidatorServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class NifValidatorService extends ValidatorServiceGrpc.ValidatorServiceImplBase {

    @Override
    public void checkNifValidityStream(NifRequest request, StreamObserver<ValidatorResponse> responseObserver) {
        String document = request.getNifDocument();

        NifValidator nifValidator = new NifValidator();

        ValidatorResponse validatorResponse = ValidatorResponse.newBuilder().setIsValid(nifValidator.validate(document)).build();
        responseObserver.onNext(validatorResponse);
        responseObserver.onCompleted();
    }
}
