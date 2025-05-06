package io.bayrktlihn.choaecommerce.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RemoveCategoryResponse {
    private Long id;
    private List<Long> categoryIds;

}
