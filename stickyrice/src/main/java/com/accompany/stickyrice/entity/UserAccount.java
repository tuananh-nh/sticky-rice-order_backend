package com.accompany.stickyrice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor


@FieldDefaults (level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user_account")
public class UserAccount {

    @Column(name = "user_id")
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    UUID userId;

    @Column(name = "username")
    String username;

    @Column(name = "image")
    String image;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "address")
    String address;

    //one to many
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAccount")
    Set<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAccount")
    Set<Order> orders;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userAccount")
    Set<VoucherUsed> voucherUseds;

    //many to one
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
}
