package io.bayrktlihn.choaecommerce.entity.jpa;


import io.bayrktlihn.choaecommerce.enums.ProductStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "product")
public class ProductJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    @Column(name = "base_price")
    private BigDecimal basePrice;

    @Column(name = "current_price")
    private BigDecimal currentPrice;

    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<CategoryJpaEntity> categories = new ArrayList<>();

    @Column(name = "stock_quantity")
    private Long stockQuantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "last_update_date")
    private Instant lastUpdateDate;

    @PrePersist
    void onPrePersist() {
        creationDate = Instant.now();
    }

    @PreUpdate
    void onPreUpdate() {
        lastUpdateDate = Instant.now();
    }

}
