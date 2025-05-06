package io.bayrktlihn.choaecommerce.repository.jpa;

import io.bayrktlihn.choaecommerce.entity.jpa.CategoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryJpaEntity, Long> {
}
