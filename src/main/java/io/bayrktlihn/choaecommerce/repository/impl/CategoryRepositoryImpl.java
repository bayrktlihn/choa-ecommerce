package io.bayrktlihn.choaecommerce.repository.impl;

import io.bayrktlihn.choaecommerce.entity.jpa.CategoryJpaEntity;
import io.bayrktlihn.choaecommerce.mapper.CategoryMapper;
import io.bayrktlihn.choaecommerce.model.Category;
import io.bayrktlihn.choaecommerce.repository.CategoryRepository;
import io.bayrktlihn.choaecommerce.repository.jpa.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category create(Category category) {

        if (category.getId() != null) {
            throw new IllegalArgumentException("Category id already exists");
        }

        CategoryJpaEntity categoryJpaEntity = categoryMapper.categoryToCategoryJpaEntity(category);

        categoryJpaEntity = categoryJpaRepository.save(categoryJpaEntity);

        return categoryMapper.categoryJpaEntityToCategory(categoryJpaEntity);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryJpaRepository.findById(categoryId)
                .map(categoryMapper::categoryJpaEntityToCategory);
    }

    @Override
    public Category update(Category category) {
        if(category.getId() == null){
            throw new IllegalArgumentException("Category id is null");
        }

        CategoryJpaEntity categoryJpaEntity = categoryMapper.categoryToCategoryJpaEntity(category);

        categoryJpaEntity = categoryJpaRepository.saveAndFlush(categoryJpaEntity);

        return categoryMapper.categoryJpaEntityToCategory(categoryJpaEntity);
    }

    @Override
    public boolean existsById(Long categoryId) {
        return categoryJpaRepository.existsById(categoryId);
    }
}
