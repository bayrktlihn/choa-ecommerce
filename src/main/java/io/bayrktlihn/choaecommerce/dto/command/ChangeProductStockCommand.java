package io.bayrktlihn.choaecommerce.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeProductStockCommand {

    @NotNull
    @Positive
    private Long productId;

    @NotNull
    @PositiveOrZero
    private Long stockQuantity;
}
