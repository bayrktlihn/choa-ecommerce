package io.bayrktlihn.choaecommerce.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class I18nSupportedException extends RuntimeException {
    private String key;
    private String defaultMessage;
    private List<Object> arguments = new ArrayList<>();

    public static I18nSupportedException createWithDefaultMessage(String defaultMessage) {
        I18nSupportedException i18nSupportedException = new I18nSupportedException();
        i18nSupportedException.setDefaultMessage(defaultMessage);
        return i18nSupportedException;
    }
}
