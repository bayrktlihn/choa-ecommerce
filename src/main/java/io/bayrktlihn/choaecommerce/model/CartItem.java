package io.bayrktlihn.choaecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItem {

    private Long id;

    // composition ise yani cartItem var olması için cart gerekiyorsa Cart olarak yapılabilir.
    private Long cartId;

    // Product olarak yapılabilir.
    private Long productId;

    private Long quantity;


}
