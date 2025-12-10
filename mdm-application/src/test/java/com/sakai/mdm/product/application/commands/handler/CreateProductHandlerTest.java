package com.sakai.mdm.product.application.commands.handler;

import com.sakai.domain.events.DomainEventPublisher;
import com.sakai.mdm.product.application.commands.CreateProductCommand;
import com.sakai.mdm.product.application.commands.dtos.*;
import com.sakai.mdm.product.domain.aggregates.Product;
import com.sakai.mdm.product.domain.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateProductHandlerTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private DomainEventPublisher domainEventPublisher;

    @InjectMocks
    private CreateProductHandler handler;

    private final VariantData variant = new VariantData(
        "SKU-001",
        Map.of("color", "red"),
        null,
        null,
        null,
        null,
        null
    );

    @Test
    void shouldCreateProductWithMinimalData() {
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

        handler.handle(command);

        verify(productRepository).save(any(Product.class));
        verify(domainEventPublisher).publishAllEvents(anyList());
    }

    @Test
    void shouldCreateProductWithDescription() {
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

        handler.handle(command);

        verify(productRepository).save(any(Product.class));
        verify(domainEventPublisher).publishAllEvents(anyList());
    }

    @Test
    void shouldCreateProductWithDimensions() {
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

        handler.handle(command);

        verify(productRepository).save(any(Product.class));
        verify(domainEventPublisher).publishAllEvents(anyList());
    }

    @Test
    void shouldCreateProductWithPrice() {
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

        handler.handle(command);

        verify(productRepository).save(any(Product.class));
        verify(domainEventPublisher).publishAllEvents(anyList());
    }

    @Test
    void shouldCreateProductWithVariants() {
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

        handler.handle(command);

        verify(productRepository).save(any(Product.class));
        verify(domainEventPublisher).publishAllEvents(anyList());
    }

    @Test
    void shouldCreateProductWithAllData() {
        var dimensions = new DimensionsData("10", "20", "5", "2");
        var price = new MoneyData("99.99", "USD");

        var command = new CreateProductCommand(
            "Test Product",
            "CODE123",
            "Complete product",
            new UomData("kg"),
            UUID.randomUUID(),
            dimensions,
            price,
            List.of(variant)
        );

        handler.handle(command);

        verify(productRepository).save(any(Product.class));
        verify(domainEventPublisher).publishAllEvents(anyList());
    }
}
