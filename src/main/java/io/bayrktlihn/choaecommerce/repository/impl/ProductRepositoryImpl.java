package io.bayrktlihn.choaecommerce.repository.impl;

import io.bayrktlihn.choaecommerce.entity.jpa.ProductJpaEntity;
import io.bayrktlihn.choaecommerce.mapper.ProductMapper;
import io.bayrktlihn.choaecommerce.model.Product;
import io.bayrktlihn.choaecommerce.repository.ProductRepository;
import io.bayrktlihn.choaecommerce.repository.jpa.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create(Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Product id already exists");
        }
        ProductJpaEntity productJpaEntity = productMapper.productToProductJpaEntity(product);

        productJpaEntity = productJpaRepository.save(productJpaEntity);

        return productMapper.productJpaEntityToProduct(productJpaEntity);
    }

    @Override
    public Optional<Product> findById(long id) {
        return productJpaRepository.findById(id)
                .map(productMapper::productJpaEntityToProduct);
    }

    @Override
    public Product update(Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product id is null");
        }
        ProductJpaEntity productJpaEntity = productMapper.productToProductJpaEntity(product);
        productJpaEntity = productJpaRepository.saveAndFlush(productJpaEntity);
        return productMapper.productJpaEntityToProduct(productJpaEntity);
    }
}
