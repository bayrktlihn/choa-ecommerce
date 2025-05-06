package io.bayrktlihn.choaecommerce.repository;

import io.bayrktlihn.choaecommerce.model.Category;

import java.util.Optional;

public interface CategoryRepository {
    Category create(Category category);

    Optional<Category> findById(Long categoryId);

    Category update(Category category);

    boolean existsById(Long categoryId);
}
