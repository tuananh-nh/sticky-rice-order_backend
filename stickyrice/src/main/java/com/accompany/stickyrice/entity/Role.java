package com.accompany.stickyrice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


@FieldDefaults (level = AccessLevel.PRIVATE)

@Entity
@Table(name = "role")

public class Role {

    @Id
    @Column(name = "role_id")
    String roleId;

    @Column(name = "role_name")
    String roleName;

    //one to many
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "role")
    Set<UserAccount> userAccounts;

    //many to one
}
