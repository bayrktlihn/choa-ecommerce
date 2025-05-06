package io.bayrktlihn.choaecommerce.entity.jpa;

import io.bayrktlihn.choaecommerce.enums.CartStatus;
import jakarta.persistence.*;
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
@Table(name = "cart")
public class CartEntityJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private CustomerJpaEntity customerJpaEntity;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @OneToMany(mappedBy = "cart")
    private List<CartItemJpaEntity> cartItems = new ArrayList<>();

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private CartStatus status;
}
