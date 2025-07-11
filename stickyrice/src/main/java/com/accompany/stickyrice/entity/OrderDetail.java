package com.accompany.stickyrice.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Integer orderDetailId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "net_price")
    private Double netPrice;

    @Column(name = "total_price")
    private Double totalPrice;

    // Many-to-One: Order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Many-to-One: Product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Constructors
    public OrderDetail() {
    }

    public OrderDetail(Integer orderDetailId, Integer quantity, Double netPrice, Double totalPrice, Order order, Product product) {
        this.orderDetailId = orderDetailId;
        this.quantity = quantity;
        this.netPrice = netPrice;
        this.totalPrice = totalPrice;
        this.order = order;
        this.product = product;
    }

    // Getters and Setters

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
