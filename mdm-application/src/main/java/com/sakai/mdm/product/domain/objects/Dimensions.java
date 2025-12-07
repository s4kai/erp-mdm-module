package com.sakai.mdm.product.domain.objects;

import com.sakai.domain.objects.BaseValueObject;
import lombok.Getter;

@Getter
public class Dimensions extends BaseValueObject {
    private final int width;
    private final int height;
    private final int depth;

    public Dimensions(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
}
