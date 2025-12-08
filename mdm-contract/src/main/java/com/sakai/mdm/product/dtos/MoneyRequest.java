package com.sakai.mdm.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MoneyRequest(
    @NotBlank
    @Pattern(regexp = "^[0-9]+(\\.[0-9]{1,2})?$", message = "Amount must be numeric")
    String amount,

    @NotBlank
    @Size(min = 3, max = 3)
    String currency
) {}
