package io.bayrktlihn.choaecommerce.dto.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginCommand {
    private String email;
    private String password;
}
