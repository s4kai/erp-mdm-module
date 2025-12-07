package com.sakai.mdm.product.domain.aggregates;

import com.sakai.exception.BusinessException;
import com.sakai.mdm.product.domain.entities.ProductVariant;
import com.sakai.mdm.product.domain.objects.*;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldAddVariantSuccessfully() {
        Product product = new Product(ProductId.create());
        SKU sku = new SKU("VAR001");
        Attributes attributes = new Attributes(Map.of("color", "red"));

        ProductVariant variant = product.addVariant(sku, attributes);

        assertNotNull(variant);
        assertEquals(sku, variant.getId());
    }

    @Test
    void shouldThrowExceptionWhenAddingDuplicateVariant() {
        Product product = new Product(ProductId.create());
        SKU sku = new SKU("VAR001");
        Attributes attributes = new Attributes(Map.of("color", "red"));

        product.addVariant(sku, attributes);

        assertThrows(BusinessException.class, () -> {
            product.addVariant(sku, attributes);
        });
    }

    @Test
    void shouldFindVariantBySku() {
        Product product = new Product(ProductId.create());
        SKU sku = new SKU("VAR001");
        Attributes attributes = new Attributes(Map.of("color", "red"));

        product.addVariant(sku, attributes);
        Optional<ProductVariant> found = product.findVariantBySku(sku);

        assertTrue(found.isPresent());
        assertEquals(sku, found.get().getId());
    }

    @Test
    void shouldReturnEmptyWhenVariantNotFound() {
        Product product = new Product(ProductId.create());
        SKU sku = new SKU("VAR001");

        Optional<ProductVariant> found = product.findVariantBySku(sku);

        assertTrue(found.isEmpty());
    }

    @Test
    void shouldActivateProduct() {
        Product product = new Product(ProductId.create());

        assertDoesNotThrow(product::activate);
    }

    @Test
    void shouldDeactivateProduct() {
        Product product = new Product(ProductId.create());

        assertDoesNotThrow(product::deactivate);
    }
}