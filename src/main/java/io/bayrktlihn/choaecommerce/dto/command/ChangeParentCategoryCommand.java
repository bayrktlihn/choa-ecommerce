package io.bayrktlihn.choaecommerce.dto.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeParentCategoryCommand {

    @NotNull
    @Positive
    private Long categoryId;

    private Long parentCategoryId;
}
