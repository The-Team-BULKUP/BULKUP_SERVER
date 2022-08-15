package com.bulkup.health.repository.redis;

import com.bulkup.health.entity.TokenStorage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenStorageRepository extends CrudRepository<TokenStorage, Long> {
    Optional<TokenStorage> findByAccessToken(String accessToken);
    Optional<TokenStorage> findByUsername(String username);
}
