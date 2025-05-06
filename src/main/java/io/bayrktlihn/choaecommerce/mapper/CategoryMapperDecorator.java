package io.bayrktlihn.choaecommerce.mapper;

import io.bayrktlihn.choaecommerce.entity.jpa.CategoryJpaEntity;
import io.bayrktlihn.choaecommerce.entity.jpa.ProductJpaEntity;
import io.bayrktlihn.choaecommerce.model.Category;
import io.bayrktlihn.choaecommerce.repository.jpa.CategoryJpaRepository;
import io.bayrktlihn.choaecommerce.repository.jpa.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.stream.Collectors;

public abstract class CategoryMapperDecorator implements CategoryMapper {

    @Autowired
    private CategoryJpaRepository categoryJpaRepository;

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    @Qualifier("delegate")
    private CategoryMapper delegate;


    @Override
    public CategoryJpaEntity categoryToCategoryJpaEntity(Category category) {
        CategoryJpaEntity categoryJpaEntity = delegate.categoryToCategoryJpaEntity(category);
        if(categoryJpaEntity == null) {
            return null;
        }

        if(category.getParentCategoryId() != null) {
            CategoryJpaEntity parentCategory = categoryJpaRepository.getReferenceById(category.getParentCategoryId());
            categoryJpaEntity.setParentCategory(parentCategory);
        }

        if(category.getProductIds() != null) {
            List<ProductJpaEntity> products = category.getProductIds().stream().map(productJpaRepository::getReferenceById).collect(Collectors.toList());
            categoryJpaEntity.setProducts(products);
        }

        return categoryJpaEntity;
    }
}
