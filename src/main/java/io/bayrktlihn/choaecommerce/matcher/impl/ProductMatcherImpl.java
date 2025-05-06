package io.bayrktlihn.choaecommerce.matcher.impl;

import io.bayrktlihn.choaecommerce.dto.command.UpdateProductCommand;
import io.bayrktlihn.choaecommerce.matcher.ProductMatcher;
import io.bayrktlihn.choaecommerce.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductMatcherImpl implements ProductMatcher {

    @Override
    public void matchUpdateProductCommandAndProduct(UpdateProductCommand command, Product product) {
        product.setDescription(command.getDescription());
        product.setBasePrice(command.getBasePrice());
        product.setCurrentPrice(command.getCurrentPrice());
        product.setCategoryIds(new ArrayList<>(command.getCategoryIds()));
        product.setStockQuantity(command.getStockQuantity());
        product.setStatus(command.getStatus());
    }
}
