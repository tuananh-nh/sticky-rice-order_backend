package com.accompany.stickyrice.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voucher_used")
public class VoucherUsed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher_used_id")
    private Integer voucherUsedId;

    @Column(name = "use_date")
    private LocalDateTime useDate;

    // many-to-one: mỗi lần dùng voucher đều gắn với 1 user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    // many-to-one: mỗi lần dùng voucher là một lần dùng 1 voucher cụ thể
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    // Constructor không tham số
    public VoucherUsed() {}

    // Constructor đầy đủ
    public VoucherUsed(Integer voucherUsedId, LocalDateTime useDate,
                       UserAccount userAccount, Voucher voucher) {
        this.voucherUsedId = voucherUsedId;
        this.useDate = useDate;
        this.userAccount = userAccount;
        this.voucher = voucher;
    }

    // Getters and Setters

    public Integer getVoucherUsedId() {
        return voucherUsedId;
    }

    public void setVoucherUsedId(Integer voucherUsedId) {
        this.voucherUsedId = voucherUsedId;
    }

    public LocalDateTime getUseDate() {
        return useDate;
    }

    public void setUseDate(LocalDateTime useDate) {
        this.useDate = useDate;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
}
