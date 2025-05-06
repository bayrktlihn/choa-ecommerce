package io.bayrktlihn.choaecommerce.dto.response;

import io.bayrktlihn.choaecommerce.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductResponse {
    private Long id;

    private String description;

    private BigDecimal basePrice;

    private BigDecimal currentPrice;

    private List<Long> categoryIds = new ArrayList<>();

    private Long stockQuantity;

    private ProductStatus status;
}
