package com.accompany.stickyrice.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "voucher")
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "voucher_id")
    private UUID voucherId;

    @Column(name = "type", nullable = false)
    private String type; // product, order

    @Column(name = "date_start", nullable = false)
    private LocalDateTime dateStart;

    @Column(name = "date_end")
    private LocalDateTime dateEnd;

    private Double discountPercent;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "description", length = 1000)
    private String description;

    // One voucher – many orders
    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Order> orders;

    // One voucher – many voucherUseds
    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VoucherUsed> voucherUseds;

    // One voucher – many products
    @OneToMany(mappedBy = "voucher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Product> products;

    // === Constructors ===

    public Voucher() {
    }

    public Voucher(UUID voucherId, String type, LocalDateTime dateStart, LocalDateTime dateEnd,
                   Double discountPercent, Boolean status, String description,
                   Set<Order> orders, Set<VoucherUsed> voucherUseds, Set<Product> products) {
        this.voucherId = voucherId;
        this.type = type;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.discountPercent = discountPercent;
        this.status = status;
        this.description = description;
        this.orders = orders;
        this.voucherUseds = voucherUseds;
        this.products = products;
    }

    // === Getters & Setters ===

    public UUID getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(UUID voucherId) {
        this.voucherId = voucherId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<VoucherUsed> getVoucherUseds() {
        return voucherUseds;
    }

    public void setVoucherUseds(Set<VoucherUsed> voucherUseds) {
        this.voucherUseds = voucherUseds;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
