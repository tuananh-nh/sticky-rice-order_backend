package com.accompany.stickyrice.repository;

import com.accompany.stickyrice.entity.UserAccount;
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

}
