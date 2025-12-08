package com.sakai.mdm.product.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Map;

public record VariantRequest(
    @NotBlank
    @Size(max = 255)
    String sku,

    @NotNull
    @Size(min = 1)
    Map<@NotBlank String, @NotBlank String> attributes,

    @NotBlank
    @Size(max = 255)
    String name,

    @NotBlank
    String gtin,

    @NotNull
    @Valid
    MoneyRequest price,

    @NotNull
    @Valid
    DimensionsRequest dimensions,

    @NotBlank
    String uom
) {}
