package com.sakai.mdm.product.application.commands;

import com.sakai.mdm.product.application.commands.dtos.DimensionsData;
import com.sakai.mdm.product.application.commands.dtos.MoneyData;
import com.sakai.mdm.product.application.commands.dtos.UomData;
import com.sakai.mdm.product.application.commands.dtos.VariantData;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public record CreateProductCommand(
        String name,
        String internCode,
        String description,
        UomData baseUom,
        UUID categoryId,
        DimensionsData baseDimensions,
        MoneyData basePrice,
        List<VariantData> variants
) {


    public boolean hasDescription() {
        return description != null && !description.isBlank();
    }

    public boolean hasDimensions() {
        return baseDimensions != null;
    }

    public boolean hasBasePrice() {
        return basePrice != null;
    }

    public boolean hasVariants() {
        return variants != null && !variants.isEmpty();
    }
}
