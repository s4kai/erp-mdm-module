package com.sakai.mdm.product.application.commands.handler;

import com.sakai.domain.events.DomainEventPublisher;
import com.sakai.domain.objects.Money;
import com.sakai.mdm.product.application.commands.CreateProductCommand;
import com.sakai.mdm.product.domain.aggregates.Product;
import com.sakai.mdm.product.domain.objects.*;
import com.sakai.mdm.product.domain.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductHandler {
    private final ProductRepository productRepository;
    private final DomainEventPublisher domainEventPublisher;

    public void handle(CreateProductCommand command) {
        var categoryId = new CategoryId(command.categoryId());
        var baseUom = new UnitOfMeasureId(command.baseUom().value());

        var newProduct = Product.create(
            command.name(),
            command.internCode(),
            categoryId,
            baseUom
        );

        if (command.hasDescription()) {
            newProduct.updateDescription(command.description());
        }

        if (command.hasDimensions()) {
            var dims = command.baseDimensions();

            newProduct.defineBaseDimensions(
                Dimensions.of(
                    dims.height(),
                    dims.width(),
                    dims.depth(),
                    dims.weight())
            );
        }

        if (command.hasBasePrice()) {
            newProduct.updateBasePrice(
                Money.of(command.basePrice().amount(), command.basePrice().currency())
            );
        }

        handleVariants(command, newProduct);

        productRepository.save(newProduct);
        domainEventPublisher.publishAllEvents(newProduct.pullDomainEvents());
    }

    private void handleVariants(CreateProductCommand command, Product newProduct) {
        if (!command.hasVariants()) return;

        command.variants().forEach(v -> {
            newProduct.addVariant(v.sku(), v.attributes());
        });
    }

}
