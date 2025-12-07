package com.sakai.mdm.product.domain.objects;

import com.sakai.domain.objects.BaseValueObject;
import lombok.Getter;

@Getter
public class Gtin extends BaseValueObject {
    private final String value;

    public Gtin(String value) {
        this.value = value;
    }
}
