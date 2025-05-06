package io.bayrktlihn.choaecommerce.model;

import io.bayrktlihn.choaecommerce.enums.PaymentMethod;
import io.bayrktlihn.choaecommerce.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class Payment {


    private Long id;

    private Long orderId;

    private BigDecimal amount;

    private PaymentMethod method;

    private PaymentStatus status;

    private Instant transactionDate;

    private String transactionReference;

}
