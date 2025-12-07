package com.sakai.mdm.product.domain.repositories;

import com.sakai.mdm.product.domain.aggregates.Product;

public interface ProductRepository {
    void save(Product product);
}
