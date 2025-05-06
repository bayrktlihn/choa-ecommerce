package io.bayrktlihn.choaecommerce.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AssignProductToCategoryResponse {
    private Long productId;
    private List<Long> categoryIds;
}
