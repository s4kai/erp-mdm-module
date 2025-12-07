package com.sakai.mdm.product.domain.events;

import com.sakai.domain.events.DomainEvent;
import com.sakai.mdm.product.domain.objects.ProductId;
import com.sakai.mdm.product.domain.objects.SKU;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVariantAdded extends DomainEvent {
    private final Instant occurredAt = Instant.now();
    private final String eventType = "PRODUCT_CREATED";
    private final ProductId productId;
    private final SKU sku;

    public  ProductVariantAdded(ProductId productId, SKU sku) {
        this.productId = productId;
        this.sku = sku;
    }
}
