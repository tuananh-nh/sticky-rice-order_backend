package com.accompany.stickyrice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "total_amount")
    private Double totalAmount;

    // One order - many order details
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private Set<OrderDetail> orderDetails;

    // Many orders - one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    // Many orders - one voucher
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    // Many orders - one status
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    // One order - one payment
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    // Constructors
    public Order() {
    }

    public Order(UUID orderId, LocalDateTime orderDate, String deliveryAddress, Integer orderStatus,
                 Double discount, Double totalAmount, Set<OrderDetail> orderDetails,
                 UserAccount userAccount, Voucher voucher, Status status, Payment payment) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.deliveryAddress = deliveryAddress;
        this.orderStatus = orderStatus;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.orderDetails = orderDetails;
        this.userAccount = userAccount;
        this.voucher = voucher;
        this.status = status;
        this.payment = payment;
    }

    // Getters & Setters

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
