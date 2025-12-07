package com.sakai.mdm.product.domain.aggregates;

import com.sakai.domain.objects.Money;
import com.sakai.exception.BusinessException;
import com.sakai.mdm.product.domain.objects.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateProduct() {
        var product = Product.create(
            "Test Product",
            "CODE123",
            new CategoryId(UUID.randomUUID()),
            new UnitOfMeasureId("uom-1")
        );

        assertNotNull(product);
        assertNotNull(product.getId());
        assertNotNull(product.getCreatedDate());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        assertThrows(BusinessException.class, () ->
            Product.create(null,
                "CODE123",
                new CategoryId(UUID.randomUUID()),
                new UnitOfMeasureId("uom-1")
            )
        );
    }

    @Test
    void shouldAddVariant() {
        var product = Product.create(
            "Test Product",
            "CODE123",
            new CategoryId(UUID.randomUUID()),
            new UnitOfMeasureId("uom-1")
        );

        var variant = product.addVariant("SKU-001", Map.of("color", "red"));

        assertNotNull(variant);
    }

    @Test
    void shouldThrowExceptionWhenAddingDuplicateVariant() {
        var product = Product.create(
            "Test Product",
            "CODE123",
            new CategoryId(UUID.randomUUID()),
            new UnitOfMeasureId("uom-1")
        );

        product.addVariant("SKU-001", Map.of("color", "red"));

        assertThrows(BusinessException.class, () ->
            product.addVariant("SKU-001", Map.of("color", "blue"))
        );
    }

    @Test
    void shouldDeactivateProduct() {
        var product = Product.create(
            "Test Product",
            "CODE123",
            new CategoryId(UUID.randomUUID()),
            new UnitOfMeasureId("uom-1")
        );

        assertDoesNotThrow(product::deactivate);
    }

    @Test
    void shouldUpdateDescription() {
        var product = Product.create(
            "Test Product",
            "CODE123",
            new CategoryId(UUID.randomUUID()),
            new UnitOfMeasureId("uom-1")
        );

        assertDoesNotThrow(() -> product.updateDescription("New description"));
    }

    @Test
    void shouldUpdateBasePrice() {
        var product = Product.create(
            "Test Product",
            "CODE123",
            new CategoryId(UUID.randomUUID()),
            new UnitOfMeasureId("uom-1")
        );

        assertDoesNotThrow(() -> product.updateBasePrice(Money.of(BigDecimal.TEN)));
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNull() {
        var product = Product.create(
            "Test Product",
            "CODE123",
            new CategoryId(UUID.randomUUID()),
            new UnitOfMeasureId("uom-1")
        );

        assertThrows(BusinessException.class, () -> product.updateBasePrice(null));
    }

    @Test
    void shouldFindVariantBySku() {
        var product = Product.create(
            "Test Product",
            "CODE123",
            new CategoryId(UUID.randomUUID()),
            new UnitOfMeasureId("uom-1")
        );

        product.addVariant("SKU-001", Map.of("color", "red"));

        var variant = product.findVariantBySku(new SKU("SKU-001"));

        assertTrue(variant.isPresent());
    }
}
