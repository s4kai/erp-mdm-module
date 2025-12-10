package com.sakai.mdm.product.domain.entities;

import com.sakai.domain.objects.Money;
import com.sakai.mdm.product.domain.objects.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductVariantTest {

    @Test
    void shouldCreateProductVariant() {
        var productId = new ProductId(UUID.randomUUID());
        var sku = new SKU("SKU-001");
        var attributes = new Attributes(Map.of("color", "red"));

        var variant = ProductVariant.create(productId, sku, attributes);

        assertNotNull(variant);
        assertEquals(sku, variant.getId());
    }

    @Test
    void shouldResolvePriceFromVariantWhenSet() {
        var productId = new ProductId(UUID.randomUUID());
        var sku = new SKU("SKU-001");
        var attributes = new Attributes(Map.of("color", "red"));
        var basePrice = Money.of(BigDecimal.TEN);

        var variant = ProductVariant.create(productId, sku, attributes);

        assertEquals(basePrice, variant.resolvePrice(basePrice));
    }

    @Test
    void shouldResolveDimensionsFromVariantWhenSet() {
        var productId = new ProductId(UUID.randomUUID());
        var sku = new SKU("SKU-001");
        var attributes = new Attributes(Map.of("color", "red"));
        var baseDimensions = new Dimensions(
            BigDecimal.valueOf(10),
            BigDecimal.valueOf(20),
            BigDecimal.valueOf(5),
            BigDecimal.valueOf(2)
        );

        var variant = ProductVariant.create(productId, sku, attributes);

        assertEquals(baseDimensions, variant.resolveDimensions(baseDimensions));
    }

    @Test
    void shouldResolveUomFromVariantWhenSet() {
        var productId = new ProductId(UUID.randomUUID());
        var sku = new SKU("SKU-001");
        var attributes = new Attributes(Map.of("color", "red"));
        var baseUom = new UnitOfMeasureId("kg");

        var variant = ProductVariant.create(productId, sku, attributes);

        assertEquals(baseUom, variant.resolveUom(baseUom));
    }

    @Test
    void shouldCheckSkuMatch() {
        var productId = new ProductId(UUID.randomUUID());
        var sku = new SKU("SKU-001");
        var attributes = new Attributes(Map.of("color", "red"));

        var variant = ProductVariant.create(productId, sku, attributes);

        assertTrue(variant.hasSku(sku));
        assertFalse(variant.hasSku(new SKU("SKU-002")));
    }

    @Test
    void shouldDeactivateVariant() {
        var productId = new ProductId(UUID.randomUUID());
        var sku = new SKU("SKU-001");
        var attributes = new Attributes(Map.of("color", "red"));

        var variant = ProductVariant.create(productId, sku, attributes);

        assertDoesNotThrow(variant::deactivate);
    }

    @Test
    void shouldActivateVariant() {
        var productId = new ProductId(UUID.randomUUID());
        var sku = new SKU("SKU-001");
        var attributes = new Attributes(Map.of("color", "red"));

        var variant = ProductVariant.create(productId, sku, attributes);

        assertDoesNotThrow(variant::activate);
    }
}