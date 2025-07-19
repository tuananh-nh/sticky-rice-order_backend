package com.accompany.stickyrice.repository;

import com.accompany.stickyrice.entity.UserAccount;
<<<<<<< HEAD
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    @Query("SELECT u FROM UserAccount u")
    Page<UserAccount> findAllUserAccount(Pageable pageable);
=======
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository <UserAccount, UUID> {
    @Query("""
       SELECT u
       FROM UserAccount u
       WHERE u.email = :email
       """)
    Optional<UserAccount> findByEmail(@Param("email") String email);
>>>>>>> 7fbcc85325be2b453674959b659dff9ac81a85c0

}
