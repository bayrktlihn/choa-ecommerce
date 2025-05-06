package io.bayrktlihn.choaecommerce.mapper;

import io.bayrktlihn.choaecommerce.entity.jpa.CategoryJpaEntity;
import io.bayrktlihn.choaecommerce.entity.jpa.ProductJpaEntity;
import io.bayrktlihn.choaecommerce.model.Product;
import io.bayrktlihn.choaecommerce.repository.jpa.CategoryJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

public abstract class ProductMapperDecorator implements ProductMapper {

    @Autowired
    @Qualifier("delegate")
    private ProductMapper productMapper;

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Override
    public ProductJpaEntity productToProductJpaEntity(Product product) {
        ProductJpaEntity productJpaEntity = productMapper.productToProductJpaEntity(product);

        if (productJpaEntity == null) {
            return null;
        }

        List<CategoryJpaEntity> categories = product.getCategoryIds().stream().map(categoryJpaRepository::getReferenceById).collect(Collectors.toList());

        productJpaEntity.setCategories(categories);

        return productJpaEntity;
    }
}
