package com.bulkup.health.repository.account;

import com.bulkup.health.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByPhone(String phone);
//    Optional<Account> findByNickname(String nickname);
}
