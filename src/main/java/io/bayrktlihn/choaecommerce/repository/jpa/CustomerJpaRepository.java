package io.bayrktlihn.choaecommerce.repository.jpa;

import io.bayrktlihn.choaecommerce.entity.jpa.CustomerJpaEntity;
import io.bayrktlihn.choaecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerJpaRepository extends JpaRepository<CustomerJpaEntity, Long> {
    boolean existsByEmail(String email);

    Optional<CustomerJpaEntity> findByEmail(String email);
}
