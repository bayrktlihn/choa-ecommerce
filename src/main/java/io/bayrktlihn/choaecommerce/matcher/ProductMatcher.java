package io.bayrktlihn.choaecommerce.matcher;

import io.bayrktlihn.choaecommerce.dto.command.UpdateProductCommand;
import io.bayrktlihn.choaecommerce.model.Product;

public interface ProductMatcher {
    void matchUpdateProductCommandAndProduct(UpdateProductCommand command, Product product);
}
