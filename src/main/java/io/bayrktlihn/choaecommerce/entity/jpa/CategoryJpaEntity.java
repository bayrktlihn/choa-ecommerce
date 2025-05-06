package io.bayrktlihn.choaecommerce.entity.jpa;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private CategoryJpaEntity parentCategory;

    @ManyToMany(mappedBy = "categories")
    private List<ProductJpaEntity> products = new ArrayList<>();

    public Long getParentCategoryId() {
        if (parentCategory == null) {
            return null;
        }
        return parentCategory.getId();
    }

    public List<Long> getProductIds() {
        if (products == null) {
            return new ArrayList<>();
        }
        return products.stream().map(ProductJpaEntity::getId).collect(Collectors.toList());
    }
}
