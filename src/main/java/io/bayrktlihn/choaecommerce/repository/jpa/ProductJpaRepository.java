package io.bayrktlihn.choaecommerce.repository.jpa;

import io.bayrktlihn.choaecommerce.entity.jpa.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductJpaEntity, Long> {
}
