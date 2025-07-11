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
@Table(name = "voucher_used")
public class VoucherUsed {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_used_id")
    Integer voucherUsedId;

    @Column(name = "use_date")
    LocalDateTime useDate;

    //one to many

    //many to one
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    Voucher voucher;
}
