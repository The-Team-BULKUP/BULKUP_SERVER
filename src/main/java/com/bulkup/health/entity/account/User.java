package com.bulkup.health.entity.account;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ROLE_USER")
public class User extends Account{
    @Column(name = "nickname", nullable = false, length = 10)
    private String nickname;
}
