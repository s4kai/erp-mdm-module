package com.sakai.mdm.product.domain.objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SKUTest {

    @Test
    void shouldCreateValidSKU() {
        var sku = new SKU("SKU-001");
        
        assertEquals("SKU-001", sku.getValue());
        assertEquals("SKU-001", sku.toString());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new SKU(null));
    }

    @Test
    void shouldThrowExceptionWhenValueIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new SKU(""));
    }

    @Test
    void shouldThrowExceptionWhenValueIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new SKU("   "));
    }

    @Test
    void shouldBeEqualWhenSameValue() {
        var sku1 = new SKU("SKU-001");
        var sku2 = new SKU("SKU-001");
        
        assertEquals(sku1, sku2);
        assertEquals(sku1.hashCode(), sku2.hashCode());
    }
}