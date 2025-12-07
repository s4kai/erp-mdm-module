package com.sakai.mdm.product.domain.objects;

import com.sakai.domain.objects.BaseValueObject;
import lombok.Getter;

import java.util.Map;

@Getter
public class Attributes extends BaseValueObject {
    private final Map<String, String> values;

    public Attributes(Map<String, String> values) {
        this.values = values;
    }

    public String get(String key) {
        return values.get(key);
    }

    public static Attributes of(String key, String value) {
        return new Attributes(Map.of(key, value));
    }
}
