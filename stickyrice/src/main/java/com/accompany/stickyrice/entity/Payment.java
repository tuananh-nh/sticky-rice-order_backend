package com.accompany.stickyrice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@FieldDefaults (level = AccessLevel.PRIVATE)
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    Integer paymentId;

    @Column (name = "payment_date")
    LocalDateTime paymentDate;

    @Column (name = "status")
    Boolean status;

    //many to one
    @ManyToOne
    @JoinColumn (name = "payment_method_id")
    PaymentMethod paymentMethod;

    //one to many


    //one to one
    @OneToOne()
    @JoinColumn (name = "order_id")
    Order order;

}
