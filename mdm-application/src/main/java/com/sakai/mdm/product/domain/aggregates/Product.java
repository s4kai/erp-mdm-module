package com.sakai.mdm.product.domain.aggregates;

import com.sakai.domain.entity.AggregateRoot;
import com.sakai.domain.objects.Money;
import com.sakai.exception.BusinessException;
import com.sakai.mdm.product.domain.entities.ProductVariant;
import com.sakai.mdm.product.domain.events.ProductVariantAdded;
import com.sakai.mdm.product.domain.objects.*;
import com.sakai.mdm.product.domain.events.ProductCreated;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Getter
    private Instant createdDate;

    private final List<ProductVariant> variants = new ArrayList<>();

    public ProductVariant addVariant(String rawSku, Map<String, String> rawAttributes) {
        SKU sku = new SKU(rawSku);
        Attributes attributes = new Attributes(rawAttributes);

        if (hasVariant(sku)) {
            throw new BusinessException("Variant with this sku already exists");
        }

        var variant = ProductVariant.create(this.getId(), sku, attributes);
        variants.add(variant);

        this.addDomainEvent(new ProductVariantAdded(this.getId(), sku));
        return variant;
    }

    private boolean hasVariant(SKU sku) {
        return variants.stream().anyMatch(v -> v.hasSku(sku));
    }

    public void deactivate() {
        status = ProductStatus.INACTIVE;
    }

    public void activate() {
        status = ProductStatus.ACTIVE;
    }

    public void updateDescription(String description) {
        if (description == null || description.isBlank()) {
            description = "PRODUTO SEM DESCRIÇÃO";
        }
        this.description = description;
    }

    public void defineBaseDimensions(Dimensions dimensions) {
        if (dimensions == null) {
            throw new BusinessException("Dimensions cannot be null");
        }
        baseDimensions = dimensions;
    }

    public void updateBasePrice(Money price) {
        if (price == null) {
            throw new BusinessException("Price cannot be null");
        }

        basePrice = price;
    }

    public Optional<ProductVariant> findVariantBySku(SKU sku) {
        return variants.stream()
                .filter(v -> v.hasSku(sku))
                .findFirst();
    }

    public static Product create(String name, String code, CategoryId categoryId, UnitOfMeasureId baseUom) {
        var productId = ProductId.create();
        var product = new Product(productId, name, code, categoryId, baseUom);
        product.addDomainEvent(new ProductCreated(productId, name, categoryId, product.getCreatedDate()));

        return product;
    }

    private Product(ProductId productId, String name, String internCode, CategoryId categoryId, UnitOfMeasureId baseUom) {
        super(productId);

        if (name == null || name.isBlank()) {
            throw new BusinessException("Product name cannot be null or empty");
        }
        if (internCode == null || internCode.isBlank()) {
            throw new BusinessException("Internal code cannot be null or empty");
        }
        if (categoryId == null) {
            throw new BusinessException("Category cannot be null");
        }
        if (baseUom == null) {
            throw new BusinessException("Base unit of measure cannot be null");
        }

        this.name = name;
        this.internCode = internCode;
        this.categoryId = categoryId;
        this.baseUom = baseUom;
        this.status = ProductStatus.ACTIVE;
        this.createdDate = Instant.now();
    }
}


