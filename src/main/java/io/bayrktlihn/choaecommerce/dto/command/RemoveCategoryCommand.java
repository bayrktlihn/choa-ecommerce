package io.bayrktlihn.choaecommerce.dto.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RemoveCategoryCommand {
    private Long productId;
    private Long categoryId;
}
