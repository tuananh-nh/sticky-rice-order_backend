package com.accompany.stickyrice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@FieldDefaults (level = AccessLevel.PRIVATE)
@Entity
@Table(name = "payment_method")

public class PaymentMethod {

    @Id
    @Column(name = "payment_method_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer paymentMethodId;

    @Column(name = "payment_method_name")
    String paymentMethodName;

    @Column(name = "description")
    String description;

    //one to many
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "paymentMethod")
    Set<Payment> payments;

    //many to one

}
