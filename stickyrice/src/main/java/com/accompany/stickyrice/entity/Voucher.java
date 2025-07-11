package com.accompany.stickyrice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@FieldDefaults (level = AccessLevel.PRIVATE)
@Entity
@Table(name = "voucher")

public class Voucher {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column(name = "voucher_id")
    UUID voucherId;

    @Column(name = "type")
    Integer type;

    @Column(name = "date_start")
    LocalDateTime dateStart;

    @Column(name = "date_end")
    LocalDateTime dateEnd;

    @Column(name = "discount_percent")
    Double discountPercent;

    @Column(name = "status")
    Boolean status;

    @Column(name = "description")
    String description;

    //one to many
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voucher")
    Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voucher")
    Set<VoucherUsed> voucherUseds;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "voucher")
    Set<Product> products;

    //many to one

}
