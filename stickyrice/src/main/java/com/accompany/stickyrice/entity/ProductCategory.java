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
@Table (name = "product_category")
@Entity
public class ProductCategory {

    @Id
    @Column(name = "category_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Integer categoryId;

    @Column(name = "category_name")
    String categoryName;

    @Column(name = "slug")
    String slug;

    //one to many
    //CascadeType.All: -- ds user desire save -> save all
    //FetchType.LAZY: data is fetched on demand
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "productCategory")
    //set: ds distinct
    Set<Product> products;

}
