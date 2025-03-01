package com.hris.harmony.repository;

import com.hris.harmony.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    UserAccount findByUsernameAndPassword(String username, String password);
    Optional<UserAccount> findByUsername(String username);
}
