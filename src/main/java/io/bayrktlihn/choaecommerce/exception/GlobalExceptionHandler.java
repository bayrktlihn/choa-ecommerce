package io.bayrktlihn.choaecommerce.exception;

import io.bayrktlihn.choaecommerce.dto.response.ErrorResponse;
import io.bayrktlihn.choaecommerce.dto.response.GenericRestResponse;
import io.bayrktlihn.choaecommerce.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.UUID;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<GenericRestResponse<?>> handleException(IllegalArgumentException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .defaultMessage(ex.getMessage())
                .build();

        GenericRestResponse<Object> restResponse = GenericRestResponse.error(errorResponse);
        return ResponseEntity.ok(restResponse);
    }


    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<GenericRestResponse<?>> handleException(IllegalStateException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .defaultMessage(ex.getMessage())
                .build();

        GenericRestResponse<Object> restResponse = GenericRestResponse.error(errorResponse);
        return ResponseEntity.ok(restResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericRestResponse<?>> handleException(Exception ex) {

        ExceptionUtil exceptionUtil = ExceptionUtil.getInstance();

        String stackTrace = exceptionUtil.getStackTrace(ex);

        String errorUUID = UUID.randomUUID().toString();

        log.info("Unknown error {}:\n {}", errorUUID, stackTrace);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .defaultMessage("Unknown error: " + errorUUID + ". Please inform us.")
                .build();

        GenericRestResponse<?> response = GenericRestResponse.error(errorResponse);
        return ResponseEntity.ok(response);
    }


    @ExceptionHandler(I18nSupportedException.class)
    public ResponseEntity<GenericRestResponse<?>> handleI18nSupportedException(final I18nSupportedException e) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .arguments(new ArrayList<>(e.getArguments()))
                .key(e.getKey())
                .defaultMessage(e.getDefaultMessage())
                .build();

        GenericRestResponse<?> response = GenericRestResponse.error(errorResponse);

        return ResponseEntity.ok(response);
    }

}
