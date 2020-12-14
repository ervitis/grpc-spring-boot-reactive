package com.orange.poc.grpc.validator.input.rest.v1.impl;

import com.orange.poc.grpc.validator.domain.nif.NifValidatorIface;
import com.orange.poc.grpc.validator.input.rest.v1.ValidatorApiRestIface;
import com.orange.poc.grpc.validator.input.rest.v1.model.NifResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Api(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "Validator NIF v1")
@RestController
@RequestMapping("/v1")
public class ValidatorApiRest implements ValidatorApiRestIface {

    @Autowired
    private final NifValidatorIface nifValidatorIface;

    public ValidatorApiRest(final NifValidatorIface nifValidatorIface) {
        this.nifValidatorIface = nifValidatorIface;
    }

    @ApiOperation(value = "Validate Nif")
    @RequestMapping(path = "/validate/nif/{document}", method = RequestMethod.POST)
    public Flux<NifResponse> validateNif(@PathVariable String document) {
        return this.nifValidatorIface.validate(document).map(NifResponse::new);
    }
}
