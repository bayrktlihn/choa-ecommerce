package io.bayrktlihn.choaecommerce.service.application;

import io.bayrktlihn.choaecommerce.dto.command.LoginCommand;
import io.bayrktlihn.choaecommerce.dto.command.RegisterNewAccountCommand;
import io.bayrktlihn.choaecommerce.dto.response.LoginResponse;
import io.bayrktlihn.choaecommerce.dto.response.LogoutResponse;
import io.bayrktlihn.choaecommerce.dto.response.RegisterNewAccountResponse;
import io.bayrktlihn.choaecommerce.exception.I18nSupportedException;
import io.bayrktlihn.choaecommerce.mapper.CustomerMapper;
import io.bayrktlihn.choaecommerce.model.Customer;
import io.bayrktlihn.choaecommerce.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerApplicationService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PasswordEncoder passwordEncoder;

    private final SessionApplicationService sessionApplicationService;

    @Transactional
    public RegisterNewAccountResponse registerNewAccount(RegisterNewAccountCommand registerNewAccountCommand) {

        boolean existsAccount = customerRepository.existsByEmail(registerNewAccountCommand.getEmail());

        if (existsAccount) {
            throw I18nSupportedException.createWithDefaultMessage("Email already in use");
        }

        Customer customer = customerMapper.registerNewAccountCommandToCustomer(registerNewAccountCommand);
        customer.setPassword(passwordEncoder.encode(registerNewAccountCommand.getPassword()));

        customer = customerRepository.create(customer);


        return customerMapper.customerToRegisterNewAccountResponse(customer);
    }

    // transactionalik bir durum yok olsun yinede böyle kalsın. Yarın öbür gün sessionlar dbye kaydedilebilir.
    @Transactional
    public LoginResponse login(String sessionId, LoginCommand loginCommand) {

        Customer customer = customerRepository.findByEmail(loginCommand.getEmail()).orElseThrow(() -> I18nSupportedException.createWithDefaultMessage("Customer does not exist"));

        if (!passwordEncoder.matches(loginCommand.getPassword(), customer.getPassword())) {
            throw I18nSupportedException.createWithDefaultMessage("Wrong password");
        }

        if (sessionApplicationService.loggedIn(customer.getEmail())) {
            throw I18nSupportedException.createWithDefaultMessage("Already logged in");
        }

        sessionApplicationService.login(customer, sessionId);

        return null;
    }


    @Transactional
    public LogoutResponse logout(String sessionId) {
        sessionApplicationService.logout(sessionId);

        return null;
    }
}
