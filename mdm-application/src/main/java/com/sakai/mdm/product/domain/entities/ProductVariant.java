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

    private ProductVariant(SKU sku, ProductId productId, Attributes attributes) {
        super(sku);
        this.productId = productId;
        this.attributes = attributes;
    }

    public static ProductVariant create(ProductId productId, SKU sku, Attributes attributes) {
        return new ProductVariant(sku, productId, attributes);
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


    public boolean hasSku(SKU sku) {
        return this.id.equals(sku);
    }

    public void deactivate() {
        productStatus = ProductStatus.INACTIVE;
    }

    public void activate() {
        productStatus = ProductStatus.ACTIVE;
    }
}
