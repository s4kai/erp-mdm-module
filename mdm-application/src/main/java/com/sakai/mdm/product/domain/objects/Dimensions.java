package com.sakai.mdm.product.domain.objects;

import com.sakai.domain.objects.BaseValueObject;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Dimensions extends BaseValueObject {
    private final BigDecimal width;
    private final BigDecimal height;
    private final BigDecimal depth;
    private final BigDecimal weight;


    public Dimensions(BigDecimal width, BigDecimal height, BigDecimal depth, BigDecimal weight) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.weight = weight;
    }

    public static Dimensions of(String width, String height, String depth, String weight) {
        try {
            return new Dimensions(
                new BigDecimal(width),
                new BigDecimal(height),
                new BigDecimal(depth),
                new BigDecimal(weight)
            );

        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid dimensions");
        }
    }
}
