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

@Table (name = "product")
@Entity
public class Product {
    @Id
    @Column (name = "product_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer productId;

    @Column (name = "product_name")
    String productName;

    @Column (name = "image_product")
    String product_image;

    @Column (name = "description")
    String description;

    @Column (name = "price")
    Double price;

    @Column (name = "is_active")
    Boolean isActive;

    @Column (name = "slug")
    String slug;

    // one to many
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    Set<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    Set<OrderDetail> orderDetails;

    // many to one
    @ManyToOne
    @JoinColumn(name = "category_id")
    ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    Voucher voucher;


}
