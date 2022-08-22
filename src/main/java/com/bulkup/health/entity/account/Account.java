package com.bulkup.health.entity.account;

import com.bulkup.health.config.spring_security.SecurityRole;
import com.bulkup.health.config.spring_security.SecurityRoleConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(
        name = "role",
        discriminatorType = DiscriminatorType.STRING,
        columnDefinition = "VARCHAR(15)")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "real_name", nullable = false, length = 10)
    private String realName;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

//    @JsonIgnore
    @Column(name = "role", updatable = false, insertable = false)
    @Convert(converter = SecurityRoleConverter.class)
    private SecurityRole role;

    @Column(name = "introduce", length = 300)
    private String introduce;

    @Column(name = "activated", nullable = false)
    private Boolean activated;

    @Transient
    public String getDiscriminatorValue(){
        //for tests
        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? null : val.value();
    }
    public boolean isUser(){
        return this.role == SecurityRole.USER;
    }
    public boolean isTrainer(){
        return this.role == SecurityRole.TRAINER;
    }
}