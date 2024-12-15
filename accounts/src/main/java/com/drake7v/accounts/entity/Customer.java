package com.drake7v.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;


    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobileNumber")
    private String mobileNumber;


}
