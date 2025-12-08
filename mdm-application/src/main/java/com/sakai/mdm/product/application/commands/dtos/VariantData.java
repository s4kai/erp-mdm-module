package com.sakai.mdm.product.application.commands.dtos;

import java.util.Map;

public record VariantData(
    String sku,
    Map<String, String> attributes,
    String name,
    String gtin,
    MoneyData price,
    DimensionsData dimensions,
    UomData uom
) {}
