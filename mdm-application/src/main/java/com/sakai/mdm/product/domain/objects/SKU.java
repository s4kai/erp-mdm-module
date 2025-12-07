package com.sakai.mdm.product.domain.objects;

import com.sakai.domain.objects.BaseValueObject;
import lombok.Getter;

@Getter
public class SKU extends BaseValueObject {
    private final String value;

    public SKU(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("SKU cannot be empty");

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
