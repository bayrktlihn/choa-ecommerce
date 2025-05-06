package io.bayrktlihn.choaecommerce.repository.impl;

import io.bayrktlihn.choaecommerce.entity.jpa.CustomerJpaEntity;
import io.bayrktlihn.choaecommerce.mapper.CustomerMapper;
import io.bayrktlihn.choaecommerce.model.Customer;
import io.bayrktlihn.choaecommerce.repository.CustomerRepository;
import io.bayrktlihn.choaecommerce.repository.jpa.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerMapper customerMapper;


    @Override
    public Optional<Customer> findById(Long id) {
        return customerJpaRepository.findById(id).map(customerMapper::customerJpaEntityToCustomer);
    }

    @Override
    public boolean existsByEmail(String email) {
        return customerJpaRepository.existsByEmail(email);
    }

    @Override
    public Customer create(Customer customer) {

        if (customer.getId() != null) {
            throw new IllegalStateException("Customer has already been created");
        }

        CustomerJpaEntity customerJpaEntity = customerMapper.customerToCustomerJpaEntity(customer);

        customerJpaEntity = customerJpaRepository.save(customerJpaEntity);

        return customerMapper.customerJpaEntityToCustomer(customerJpaEntity);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerJpaRepository.findByEmail(email).map(customerMapper::customerJpaEntityToCustomer);
    }
}
