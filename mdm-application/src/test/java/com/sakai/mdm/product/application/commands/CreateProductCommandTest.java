package com.sakai.mdm.product.application.commands;

import com.sakai.mdm.product.application.commands.dtos.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CreateProductCommandTest {

    @Test
    void shouldReturnTrueWhenHasDescription() {
        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            "Product description",
            new UomData("kg"),
            UUID.randomUUID(),
            null,
            null,
            null
        );

        assertTrue(command.hasDescription());
    }

    @Test
    void shouldReturnFalseWhenDescriptionIsNull() {
        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            null,
            new UomData("kg"),
            UUID.randomUUID(),
            null,
            null,
            null
        );

        assertFalse(command.hasDescription());
    }

    @Test
    void shouldReturnFalseWhenDescriptionIsBlank() {
        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            "   ",
            new UomData("kg"),
            UUID.randomUUID(),
            null,
            null,
            null
        );

        assertFalse(command.hasDescription());
    }

    @Test
    void shouldReturnTrueWhenHasDimensions() {
        var dimensions = new DimensionsData("10", "20", "5", "2");
        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            null,
            new UomData("kg"),
            UUID.randomUUID(),
            dimensions,
            null,
            null
        );

        assertTrue(command.hasDimensions());
    }

    @Test
    void shouldReturnFalseWhenDimensionsIsNull() {
        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            null,
            new UomData("kg"),
            UUID.randomUUID(),
            null,
            null,
            null
        );

        assertFalse(command.hasDimensions());
    }

    @Test
    void shouldReturnTrueWhenHasBasePrice() {
        var price = new MoneyData("99.99", "USD");
        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            null,
            new UomData("kg"),
            UUID.randomUUID(),
            null,
            price,
            null
        );

        assertTrue(command.hasBasePrice());
    }

    @Test
    void shouldReturnFalseWhenBasePriceIsNull() {
        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            null,
            new UomData("kg"),
            UUID.randomUUID(),
            null,
            null,
            null
        );

        assertFalse(command.hasBasePrice());
    }

    @Test
    void shouldReturnTrueWhenHasVariants() {
        var variant = new VariantData(
            "SKU-001",
            Map.of("color", "red"),
            null,
            null,
            null,
            null,
            null
        );

        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            null,
            new UomData("kg"),
            UUID.randomUUID(),
            null,
            null,
            List.of(variant)
        );

        assertTrue(command.hasVariants());
    }

    @Test
    void shouldReturnFalseWhenVariantsIsNull() {
        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            null,
            new UomData("kg"),
            UUID.randomUUID(),
            null,
            null,
            null
        );

        assertFalse(command.hasVariants());
    }

    @Test
    void shouldReturnFalseWhenVariantsIsEmpty() {
        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            null,
            new UomData("kg"),
            UUID.randomUUID(),
            null,
            null,
            List.of()
        );

        assertFalse(command.hasVariants());
    }
}
