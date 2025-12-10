package com.sakai.mdm.product.domain.objects;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class DimensionsTest {

    @Test
    void shouldCreateDimensionsWithBigDecimal() {
        var dimensions = new Dimensions(
            BigDecimal.valueOf(10.5),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(5.5),
            BigDecimal.valueOf(2.5)
        );

        assertEquals(BigDecimal.valueOf(10.5), dimensions.getWidth());
        assertEquals(BigDecimal.valueOf(20.0), dimensions.getHeight());
        assertEquals(BigDecimal.valueOf(5.5), dimensions.getDepth());
        assertEquals(BigDecimal.valueOf(2.5), dimensions.getWeight());
    }

    @Test
    void shouldCreateDimensionsFromStrings() {
        var dimensions = Dimensions.of("10.5", "20.0", "5.5", "2.5");

        assertEquals(new BigDecimal("10.5"), dimensions.getWidth());
        assertEquals(new BigDecimal("20.0"), dimensions.getHeight());
        assertEquals(new BigDecimal("5.5"), dimensions.getDepth());
        assertEquals(new BigDecimal("2.5"), dimensions.getWeight());
    }

    @Test
    void shouldThrowExceptionForInvalidStringValues() {
        assertThrows(IllegalArgumentException.class, () ->
            Dimensions.of("invalid", "20.0", "5.5", "2.5")
        );
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        var dimensions1 = new Dimensions(
            BigDecimal.valueOf(10.5),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(5.5),
            BigDecimal.valueOf(2.5)
        );
        
        var dimensions2 = new Dimensions(
            BigDecimal.valueOf(10.5),
            BigDecimal.valueOf(20.0),
            BigDecimal.valueOf(5.5),
            BigDecimal.valueOf(2.5)
        );

        assertEquals(dimensions1, dimensions2);
        assertEquals(dimensions1.hashCode(), dimensions2.hashCode());
    }
}