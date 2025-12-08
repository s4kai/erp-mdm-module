package com.sakai.mdm.product.api;

import com.sakai.mdm.product.dtos.DimensionsRequest;
import com.sakai.mdm.product.dtos.MoneyRequest;
import com.sakai.mdm.product.dtos.VariantRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record CreateProductRequest(
    @NotBlank
    @Size(max = 255)
    String name,

    @NotBlank
    @Size(max = 100)
    String internCode,

    @Size(max = 1000)
    String description,

    @NotBlank
    String baseUom,

    @NotNull
    UUID categoryId,

    @NotNull
    @Valid
    DimensionsRequest baseDimensions,

    @NotNull
    @Valid
    MoneyRequest basePrice,

    @NotNull
    @Size(min = 1)
    @Valid
    List<VariantRequest> variants

) { }

