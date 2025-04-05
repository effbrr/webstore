package com.fb.webstore.api.customer.address;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;
    private String postalCode;

    @Enumerated(EnumType.STRING)
    private County county;

    @Enumerated(EnumType.STRING)
    private Country country;

}
