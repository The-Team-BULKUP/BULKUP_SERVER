package com.bulkup.health.config.spring_security;

import com.bulkup.health.entity.account.Account;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@Slf4j
@Getter
public class SecurityUser extends User {
    Account account;
    public SecurityUser(Account account) {
        super(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole().toString()));
        this.account = account;
    }
}
