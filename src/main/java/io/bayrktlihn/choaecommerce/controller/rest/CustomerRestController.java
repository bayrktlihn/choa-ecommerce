package io.bayrktlihn.choaecommerce.controller.rest;

import io.bayrktlihn.choaecommerce.dto.command.LoginCommand;
import io.bayrktlihn.choaecommerce.dto.command.RegisterNewAccountCommand;
import io.bayrktlihn.choaecommerce.dto.response.GenericRestResponse;
import io.bayrktlihn.choaecommerce.dto.response.LoginResponse;
import io.bayrktlihn.choaecommerce.dto.response.LogoutResponse;
import io.bayrktlihn.choaecommerce.dto.response.RegisterNewAccountResponse;
import io.bayrktlihn.choaecommerce.exception.I18nSupportedException;
import io.bayrktlihn.choaecommerce.service.application.CustomerApplicationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerApplicationService customerApplicationService;


    @PostMapping("register")
    public GenericRestResponse<RegisterNewAccountResponse> registerNewAccount(@Valid @RequestBody RegisterNewAccountCommand registerNewAccountCommand) {
        RegisterNewAccountResponse registerNewAccountResponse = customerApplicationService.registerNewAccount(registerNewAccountCommand);
        return GenericRestResponse.success(registerNewAccountResponse);
    }

    @PostMapping("login")
    public GenericRestResponse<LoginResponse> login(@Valid @RequestBody LoginCommand loginCommand, HttpServletRequest httpServletRequest) {
        String sessionId = (String) httpServletRequest.getAttribute("sessionId");

        checkSessionId(sessionId);

        LoginResponse login = customerApplicationService.login(sessionId, loginCommand);

        return GenericRestResponse.success(login);
    }

    @PostMapping("logout")
    public GenericRestResponse<LogoutResponse> logout(HttpServletRequest httpServletRequest) {
        String sessionId = (String) httpServletRequest.getAttribute("sessionId");

        checkSessionId(sessionId);

        LogoutResponse logoutResponse = customerApplicationService.logout(sessionId);

        return GenericRestResponse.success(logoutResponse);
    }

    private static void checkSessionId(String sessionId) {
        if (sessionId == null || sessionId.isEmpty()) {
            throw I18nSupportedException.createWithDefaultMessage("Invalid session id");
        }
    }

}
