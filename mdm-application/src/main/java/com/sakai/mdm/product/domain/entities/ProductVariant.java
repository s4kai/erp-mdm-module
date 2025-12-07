package com.sakai.mdm.product.domain.entities;

import com.sakai.domain.entity.BaseEntity;
import com.sakai.domain.objects.Money;
import com.sakai.mdm.product.domain.objects.*;

public class ProductVariant extends BaseEntity<SKU> {
    private ProductId productId;
    private ProductStatus productStatus;

    private Attributes attributes;
    private Dimensions dimensions;
    private String name;
    private Money price;
    private UnitOfMeasureId uom;
    private Gtin gtin;

    public ProductVariant(SKU sku, ProductId productId, Attributes attributes) {
        super(sku);
    }

    public Money resolvePrice(Money basePrice) {
        return price != null ? price : basePrice;
    }

    public Dimensions resolveDimensions(Dimensions baseDimensions) {
        return dimensions != null ? dimensions : baseDimensions;
    }

    public UnitOfMeasureId resolveUom(UnitOfMeasureId baseUom) {
        return uom != null ? uom : baseUom;
    }

    public void desactivate() {
        productStatus = ProductStatus.INACTIVE;
    }

    public void activate() {
        productStatus = ProductStatus.ACTIVE;
    }
}
