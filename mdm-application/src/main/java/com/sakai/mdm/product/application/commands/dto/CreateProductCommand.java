package com.sakai.mdm.product.application.commands.dto;

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

    public record UomData(String value) {
    }

    public record DimensionsData(
            String height,
            String width,
            String depth,
            String weight
    ) {
    }

    public record MoneyData(
            String amount,
            String currency
    ) {
    }

    public record VariantData(
            String sku,
            Map<String, String> attributes,
            String name,
            String gtin,
            MoneyData price,
            DimensionsData dimensions,
            UomData uom
    ) {
    }

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
