package com.sakai.mdm.product.dtos;

import jakarta.validation.constraints.NotBlank;

public record DimensionsRequest(
    @NotBlank String height,
    @NotBlank String width,
    @NotBlank String depth,
    @NotBlank String weight
) {}
