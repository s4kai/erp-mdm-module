package com.sakai.mdm.product.domain.objects;

import com.sakai.domain.objects.BaseValueObject;
import com.sakai.exception.BusinessException;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CategoryId extends BaseValueObject {
    private final UUID id;

    public CategoryId(UUID id) {
        if(id == null) throw new BusinessException("CategoryId Invalido");
        this.id = id;
    }
}
