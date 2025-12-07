package com.sakai.mdm.product.domain.objects;

import com.sakai.domain.objects.BaseValueObject;
import lombok.Getter;

@Getter
public class ProductStatus extends BaseValueObject {
    private final String status;

    private ProductStatus(String status) {
        this.status = status;
    }

    public static final ProductStatus ACTIVE = new ProductStatus("ACTIVE");
    public static final ProductStatus INACTIVE = new ProductStatus("INACTIVE");
    public static final ProductStatus DELETED = new ProductStatus("DELETED");
    public static final ProductStatus BLOCKED = new ProductStatus("BLOCKED");
}
