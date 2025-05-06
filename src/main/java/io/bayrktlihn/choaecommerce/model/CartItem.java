package io.bayrktlihn.choaecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    private Long id;

    private Long cartId;

    private Long productId;

    private Long quantity;


}
