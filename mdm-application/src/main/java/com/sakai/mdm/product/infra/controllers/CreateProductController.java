package com.sakai.mdm.product.infra.controllers;

import com.sakai.logging.DomainLogger;
import com.sakai.mdm.product.api.CreateProductRequest;
import com.sakai.mdm.product.application.commands.handler.CreateProductHandler;
import com.sakai.mdm.product.infra.mappers.CreateProductMapper;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class CreateProductController {

    private final CreateProductHandler handler;
    private final CreateProductMapper mapper;
    private final DomainLogger log = DomainLogger.get(CreateProductController.class);

    @PostMapping
    public ResponseEntity<Void> createProduct(@Valid @RequestBody CreateProductRequest request) {
        var command = mapper.toCommand(request);
        log.info("Received request to create product: {}", command);

        handler.handle(command);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
