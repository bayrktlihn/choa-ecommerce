package io.bayrktlihn.choaecommerce.mapper;

import io.bayrktlihn.choaecommerce.dto.command.CreateCategoryCommand;
import io.bayrktlihn.choaecommerce.dto.response.ChangeParentCategoryResponse;
import io.bayrktlihn.choaecommerce.dto.response.CreateCategoryResponse;
import io.bayrktlihn.choaecommerce.entity.jpa.CategoryJpaEntity;
import io.bayrktlihn.choaecommerce.model.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper
@DecoratedWith(CategoryMapperDecorator.class)
public interface CategoryMapper {
    @Mapping(target = "productIds", ignore = true)
    @Mapping(target = "parentCategoryId", ignore = true)
    Category categoryJpaEntityToCategory(CategoryJpaEntity categoryJpaEntity);

    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "products", ignore = true)
    CategoryJpaEntity categoryToCategoryJpaEntity(Category category);

    @AfterMapping
    default void categoryJpaEntityToCategory(CategoryJpaEntity categoryJpaEntity, @MappingTarget Category category) {
        Long parentCategoryId = categoryJpaEntity.getParentCategoryId();
        List<Long> productIds = categoryJpaEntity.getProductIds();
        category.setParentCategoryId(parentCategoryId);
        category.setProductIds(productIds);
    }

    Category createCategoryCommandToCategory(CreateCategoryCommand createCategoryCommand);

    CreateCategoryResponse categoryToCreateCategoryResponse(Category category);

    ChangeParentCategoryResponse categoryToChangeParentCategoryResponse(Category category);
}
