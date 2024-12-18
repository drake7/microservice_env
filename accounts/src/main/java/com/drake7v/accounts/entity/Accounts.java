package com.drake7v.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter@Setter@ToString@NoArgsConstructor@AllArgsConstructor
public class Accounts extends BaseEntity{

    @Column(name = "customer_id")
    private Long customerId;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    private Long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;


}
