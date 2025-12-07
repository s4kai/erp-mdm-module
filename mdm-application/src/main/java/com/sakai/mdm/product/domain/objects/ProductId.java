package com.sakai.mdm.product.domain.objects;

import com.sakai.domain.objects.BaseValueObject;
import com.sakai.exception.BusinessException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class ProductId extends BaseValueObject {
    private final UUID id;

    public static ProductId create(){
        return new ProductId(UUID.randomUUID());
    }

    public ProductId(UUID id) {
        if(id == null) throw new BusinessException("ProductId Invalido");
        this.id = id;
    }
}