package io.bayrktlihn.choaecommerce.dto.command;

import io.bayrktlihn.choaecommerce.enums.ProductStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductCommand {

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @NotEmpty
    private String description;

    @NotNull
    @Positive
    private BigDecimal basePrice;

    @NotNull
    @Positive
    private BigDecimal currentPrice;

    @NotNull
    @NotEmpty
    private List<Long> categoryIds = new ArrayList<>();

    @NotNull
    @PositiveOrZero
    private Long stockQuantity;

    @NotNull
    private ProductStatus status;
}
