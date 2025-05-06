package io.bayrktlihn.choaecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    private Long id;

    // OrderItemin var olması için product gerekiyorsa Product yapılabilir.
    private Long productId;

    private Long quantity;

    private BigDecimal price;

    private Long orderId;

}
