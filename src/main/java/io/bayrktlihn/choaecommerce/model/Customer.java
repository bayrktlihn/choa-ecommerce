package io.bayrktlihn.choaecommerce.model;

import io.bayrktlihn.choaecommerce.enums.MembershipTier;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    private String addressInformation;

    private String phoneNumber;

    private Instant accountCreationDate;

    private MembershipTier membershipTier;

}
