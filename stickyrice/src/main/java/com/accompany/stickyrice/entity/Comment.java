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

@Table (name = "comment")
@Entity
public class Comment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Integer commentId;

    @Column(name = "rating")
    Integer rating;

    @Column(name = "content")
    String content;

    @Column(name = "comment_date")
    LocalDateTime commentDate;

    //one to many



    //many to one
    @ManyToOne
    @JoinColumn(name = "user_id")
    UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
