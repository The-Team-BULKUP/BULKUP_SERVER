package com.bulkup.health.repository.account;

import com.bulkup.health.entity.account.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);

}
