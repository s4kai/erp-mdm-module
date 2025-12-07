package com.sakai.mdm.product.domain.events;

import com.sakai.domain.events.DomainEvent;
import com.sakai.mdm.product.domain.objects.CategoryId;
import com.sakai.mdm.product.domain.objects.ProductId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductCreated extends DomainEvent {
    private Instant occurredAt = Instant.now();
    private String eventType = "PRODUCT_CREATED";

    private final ProductId productId;
    private final String name;
    private final CategoryId categoryId;
    private final Instant createdDate;
}
