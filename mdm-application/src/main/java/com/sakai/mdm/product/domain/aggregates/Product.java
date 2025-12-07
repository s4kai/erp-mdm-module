package com.sakai.mdm.product.domain.aggregates;

import com.sakai.domain.entity.AggregateRoot;
import com.sakai.domain.objects.Money;
import com.sakai.exception.BusinessException;
import com.sakai.mdm.product.domain.entities.ProductVariant;
import com.sakai.mdm.product.domain.objects.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Product extends AggregateRoot<ProductId> {
    private String name;
    private String internCode;
    private String description;
    private CategoryId categoryId;
    private UnitOfMeasureId baseUom;

    private Dimensions baseDimensions;
    private Money basePrice;
    private Gtin baseGtin;
    private ProductStatus status;

    private LocalDateTime createdDate;

    private final List<ProductVariant> variants = new ArrayList<>();

    public ProductVariant addVariant(SKU sku, Attributes attributes) {
        if (sku == null) {
            throw new BusinessException("SKU cannot be null");
        }

        if (attributes == null) {
            throw new BusinessException("Attributes cannot be null");
        }

        if (hasVariant(sku)) {
            throw new BusinessException("Variant with this sku already exists");
        }

        ProductVariant variant = new ProductVariant(
                sku,
                getId(),
                attributes
        );

        variants.add(variant);
        return variant;
    }

    private boolean hasVariant(SKU sku) {
        return variants.stream().anyMatch(v -> v.getId().equals(sku));
    }

    public void deactivate() {
        status = ProductStatus.INACTIVE;
    }

    public void activate() {
        status = ProductStatus.ACTIVE;
    }

    public Optional<ProductVariant> findVariantBySku(SKU sku) {
        return variants.stream()
                .filter(v -> v.getId().equals(sku))
                .findFirst();
    }

    protected Product(ProductId productId) {
        super(productId);
    }
}


