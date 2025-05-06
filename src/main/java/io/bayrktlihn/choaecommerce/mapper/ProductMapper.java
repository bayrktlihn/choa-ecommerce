package io.bayrktlihn.choaecommerce.mapper;

import io.bayrktlihn.choaecommerce.dto.command.CreateProductWithDetailsCommand;
import io.bayrktlihn.choaecommerce.dto.response.AssignProductToCategoryResponse;
import io.bayrktlihn.choaecommerce.dto.response.ChangeProductStockResponse;
import io.bayrktlihn.choaecommerce.dto.response.CreateProductWithDetailsResponse;
import io.bayrktlihn.choaecommerce.dto.response.RemoveCategoryResponse;
import io.bayrktlihn.choaecommerce.dto.response.UpdateProductResponse;
import io.bayrktlihn.choaecommerce.entity.jpa.CategoryJpaEntity;
import io.bayrktlihn.choaecommerce.entity.jpa.ProductJpaEntity;
import io.bayrktlihn.choaecommerce.model.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper {

    Product createProductWithDetailsCommandToProduct(CreateProductWithDetailsCommand createProductWithDetailsCommand);

    @Mapping(ignore = true, target = "categories")
    ProductJpaEntity productToProductJpaEntity(Product product);

    @Mapping(ignore = true, target = "categoryIds")
    Product productJpaEntityToProduct(ProductJpaEntity productJpaEntity);

    @AfterMapping
    default void productJpaEntityToProduct(ProductJpaEntity productJpaEntity, @MappingTarget Product product) {
        List<CategoryJpaEntity> categories = productJpaEntity.getCategories();
        categories = categories == null ? new ArrayList<>() : categories;
        List<Long> categoryIds = categories.stream().map(CategoryJpaEntity::getId).collect(Collectors.toList());
        product.setCategoryIds(categoryIds);
    }

    CreateProductWithDetailsResponse productToCreateProductWithDetailsResponse(Product product);

    @Mapping(target = "productId", source = "id")
    AssignProductToCategoryResponse productToAssignProductToCategoryResponse(Product product);

    RemoveCategoryResponse productToRemoveCategoryResponse(Product product);

    UpdateProductResponse productToUpdateProductResponse(Product product);

    ChangeProductStockResponse productToChangeProductStockResponse(Product product);
}

