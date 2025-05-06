package io.bayrktlihn.choaecommerce.repository;

import io.bayrktlihn.choaecommerce.model.Product;

import java.util.Optional;

public interface ProductRepository {
    Product create(Product product);

    Optional<Product> findById(long id);

    Product update(Product product);
}
