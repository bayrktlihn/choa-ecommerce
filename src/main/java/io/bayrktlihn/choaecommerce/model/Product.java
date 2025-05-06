package io.bayrktlihn.choaecommerce.model;


import io.bayrktlihn.choaecommerce.enums.ProductStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    private Long id;

    private String description;

    private BigDecimal basePrice;

    private BigDecimal currentPrice;

    private List<Long> categoryIds = new ArrayList<>();

    private Long stockQuantity;

    private ProductStatus status;

    private Instant creationDate;

    private Instant lastUpdateDate;

    public boolean addCategoryId(Long id) {
        if (containsCategoryId(id)) {
            throw new IllegalStateException("Category id " + id + " already exists");
        }
        return categoryIds.add(id);
    }

    public boolean containsCategoryId(Long id) {
        return categoryIds.stream().anyMatch(id::equals);
    }

    public void removeCategoryId(Long categoryId) {
        if (!containsCategoryId(categoryId)) {
            throw new IllegalStateException("Category id " + categoryId + " does not exist");
        }
        categoryIds.remove(categoryId);
    }

    public void changeStockQuantity(Long stockQuantity) {
        if (stockQuantity == null || stockQuantity <= 0) {
            throw new IllegalArgumentException("Stock quantity must be greater than zero");
        }
        this.stockQuantity = stockQuantity;
    }
}
