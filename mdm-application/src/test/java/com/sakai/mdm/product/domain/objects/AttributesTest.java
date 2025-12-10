package com.sakai.mdm.product.domain.objects;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AttributesTest {

    @Test
    void shouldCreateAttributesWithMap() {
        var attributes = new Attributes(Map.of("color", "red", "size", "M"));

        assertEquals("red", attributes.get("color"));
        assertEquals("M", attributes.get("size"));
        assertNull(attributes.get("nonexistent"));
    }

    @Test
    void shouldCreateAttributesWithSingleKeyValue() {
        var attributes = Attributes.of("color", "blue");

        assertEquals("blue", attributes.get("color"));
        assertEquals(1, attributes.getValues().size());
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        var attributes1 = new Attributes(Map.of("color", "red", "size", "M"));
        var attributes2 = new Attributes(Map.of("color", "red", "size", "M"));

        assertEquals(attributes1, attributes2);
        assertEquals(attributes1.hashCode(), attributes2.hashCode());
    }

    @Test
    void shouldReturnCorrectValues() {
        var values = Map.of("color", "red", "size", "M");
        var attributes = new Attributes(values);

        assertEquals(values, attributes.getValues());
    }
}