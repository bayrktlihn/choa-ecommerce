package io.bayrktlihn.choaecommerce.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {


    private Long code;
    private String message;
    private String defaultMessage;
    private String key;
    private List<Object> arguments = new ArrayList<>();
}
