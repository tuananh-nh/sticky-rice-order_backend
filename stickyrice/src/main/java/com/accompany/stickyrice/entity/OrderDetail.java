package com.accompany.stickyrice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@FieldDefaults (level = AccessLevel.PRIVATE)
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    Integer orderDetailId;

    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "net_price")
    Double netPrice;

    @Column(name = "total_price")
    Double totalPrice;

    //one to many


    //many to one
    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
