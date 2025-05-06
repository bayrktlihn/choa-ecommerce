package io.bayrktlihn.choaecommerce.entity.jpa;

import io.bayrktlihn.choaecommerce.enums.MembershipTier;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Column(name = "address_information")
    private String addressInformation;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "account_creation_date")
    private Instant accountCreationDate;

    @Column(name = "membership_tier")
    @Enumerated(EnumType.STRING)
    private MembershipTier membershipTier;

    @PrePersist
    void onPrePersist() {
        accountCreationDate = Instant.now();
    }

}
