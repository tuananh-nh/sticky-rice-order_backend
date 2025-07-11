package com.accompany.stickyrice.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "content")
    private String content;

    @Column(name = "comment_date")
    private LocalDateTime commentDate;

    // Many comments - one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    // Many comments - one product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Constructors
    public Comment() {}

    public Comment(Integer commentId, Integer rating, String content, LocalDateTime commentDate, UserAccount userAccount, Product product) {
        this.commentId = commentId;
        this.rating = rating;
        this.content = content;
        this.commentDate = commentDate;
        this.userAccount = userAccount;
        this.product = product;
    }

    // Getters & Setters
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
