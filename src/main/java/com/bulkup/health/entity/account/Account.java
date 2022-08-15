package com.bulkup.health.entity.account;

import javax.persistence.*;

@Entity
@DiscriminatorColumn(name = "role")
@Inheritance(strategy = InheritanceType.JOINED) // 조인전략
public abstract class Account {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "real_name", nullable = false, length = 10)
    private String realName;

    @Column(name = "role", length = 10)
    private String role;

    @Column(name = "introduce", length = 300)
    private String introduce;

    public Long getId() {
        return id;
    }
}