package com.bulkup.health.util;

import com.bulkup.health.entity.TokenStorage;
import com.bulkup.health.repository.redis.TokenStorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RedisUtil {

    private final TokenStorageRepository refreshTokenRepository;
    private final long refreshTokenValidityInMilliseconds;
    
    public RedisUtil(TokenStorageRepository refreshTokenRepository,
                     @Value("${jwt.refresh-token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    public void insertTokenToStorage(String username, TokenStorage refreshTokenEntity){
        //username으로 토큰이 존재하면 삭제하고 등록
        Optional<TokenStorage> findUser = refreshTokenRepository.findByUsername(username);
        findUser.ifPresent(tokenStorage -> refreshTokenRepository.deleteById(tokenStorage.getTokenId()));

        refreshTokenEntity.setExpiration(refreshTokenValidityInMilliseconds);
        refreshTokenRepository.save(refreshTokenEntity);
    }

}
