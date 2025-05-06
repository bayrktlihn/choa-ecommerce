package io.bayrktlihn.choaecommerce.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Category {

    private Long id;

    private String name;

    private String description;

    private Long parentCategoryId;

    private List<Long> productIds = new ArrayList<>();

    public boolean addProductId(Long productId) {
        if (containsProductId(productId)) {
            throw new IllegalArgumentException("Product id " + productId + " already exists");
        }
        return productIds.add(productId);
    }

    public boolean containsProductId(Long productId) {
        return productIds.stream().anyMatch(productId::equals);
    }

    public void changeParentCategoryId(Long parentCategoryId) {
        if(id.equals(parentCategoryId)) {
            throw new IllegalArgumentException("Parent category id should not be the same as the current category id");
        }
        setParentCategoryId(parentCategoryId);
    }
}
