package com.accompany.stickyrice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@FieldDefaults (level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column(name = "order_id")
    UUID orderId;

    @Column(name = "order_date")
    LocalDateTime orderDate;

    @Column(name = "delivery_address")
    String deliveryAddress;

    @Column(name = "order_status")
    Integer orderStatus;

    @Column(name = "discount")
    Double discount;

    @Column(name = "total_amount")
    Double totalAmount;

    //one to many
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    Set<OrderDetail> orderDetails;

    //many to one
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "status_id")
    Status status;

    //one to one
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    Payment payment;
}
