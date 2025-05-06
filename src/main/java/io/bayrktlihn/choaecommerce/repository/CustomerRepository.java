package io.bayrktlihn.choaecommerce.repository;

import io.bayrktlihn.choaecommerce.model.Customer;

import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findById(Long id);

    boolean existsByEmail(String email);

    Customer create(Customer customer);

    Optional<Customer> findByEmail(String email);
}
