package io.bayrktlihn.choaecommerce.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GenericRestResponse<T> {

    private T data;
    private boolean success;
    private List<ErrorResponse> errors = new ArrayList<>();


    public static <T> GenericRestResponse<T> success(T data) {
        GenericRestResponse<T> genericRestResponse = new GenericRestResponse<>();
        genericRestResponse.setSuccess(true);
        genericRestResponse.setData(data);
        return genericRestResponse;
    }

    public static <T> GenericRestResponse<T> error(ErrorResponse error) {
        GenericRestResponse<T> genericRestResponse = new GenericRestResponse<>();
        genericRestResponse.setSuccess(false);
        genericRestResponse.setErrors(Arrays.asList(error));
        return genericRestResponse;
    }
}
