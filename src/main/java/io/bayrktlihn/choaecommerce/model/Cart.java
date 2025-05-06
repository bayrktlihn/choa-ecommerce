package io.bayrktlihn.choaecommerce.model;

import io.bayrktlihn.choaecommerce.enums.CartStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Cart {

    private Long id;

    private Long customerId;

    private Instant creationDate;

    private Instant lastUpdatedDate;

    private List<Long> cartItemIds = new ArrayList<>();

    private BigDecimal totalPrice;

    private CartStatus status;
}
