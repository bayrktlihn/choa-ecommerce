package io.bayrktlihn.choaecommerce.mapper;

import io.bayrktlihn.choaecommerce.dto.command.RegisterNewAccountCommand;
import io.bayrktlihn.choaecommerce.dto.response.RegisterNewAccountResponse;
import io.bayrktlihn.choaecommerce.entity.jpa.CustomerJpaEntity;
import io.bayrktlihn.choaecommerce.model.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerJpaEntity customerToCustomerJpaEntity(Customer customer);

    Customer registerNewAccountCommandToCustomer(RegisterNewAccountCommand registerNewAccountCommand);

    Customer customerJpaEntityToCustomer(CustomerJpaEntity customerJpaEntity);

    RegisterNewAccountResponse customerToRegisterNewAccountResponse(Customer customer);
}
