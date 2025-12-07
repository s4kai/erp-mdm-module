package com.sakai.mdm.product.domain.objects;

import com.sakai.domain.objects.BaseValueObject;
import com.sakai.exception.BusinessException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UnitOfMeasureId extends BaseValueObject {
    private final String id;

    public UnitOfMeasureId(String id) {
        if(id == null) throw new BusinessException("UomId Invalido");
        this.id = id;
    }
}
