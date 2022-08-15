package com.bulkup.health.entity.account;

import com.bulkup.health.config.spring_security.SecurityRole;
import com.bulkup.health.config.spring_security.SecurityRoleConverter;

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
    @Convert(converter = SecurityRoleConverter.class)
    private SecurityRole role;

    @Column(name = "introduce", length = 300)
    private String introduce;

    public Long getId() {
        return id;
    }

    @Transient
    public String getDiscriminatorValue(){
        //for tests
        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? null : val.value();
    }
}