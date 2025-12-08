package com.sakai.mdm.product.infra.persistence.write;

import com.sakai.logging.DomainLogger;
import com.sakai.mdm.product.domain.aggregates.Product;
import com.sakai.mdm.product.domain.repositories.ProductRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryJPAImpl implements ProductRepository {

    private final DomainLogger logger = DomainLogger.get(ProductRepositoryJPAImpl.class);

    @Override
    public void save(Product product) {
        logger.info("Produto salvo");
    }
}
