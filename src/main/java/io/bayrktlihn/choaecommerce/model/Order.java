package io.bayrktlihn.choaecommerce.model;

import io.bayrktlihn.choaecommerce.enums.OrderStatus;
import io.bayrktlihn.choaecommerce.enums.PaymentMethod;
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
public class Order {

    private Long id;

    private Long customerId;

    private Instant orderDate;

    private OrderStatus status;

    private String billingAddress;

    private String shippingAddress;

    private PaymentMethod paymentMethod;

    private List<Long> orderItemIds = new ArrayList<>();

    private BigDecimal subtotal;

    private BigDecimal shippingCost;

    private BigDecimal taxAmount;

    private BigDecimal discountAmount;

    private BigDecimal totalPrice;

    private String trackingInformation;
}
