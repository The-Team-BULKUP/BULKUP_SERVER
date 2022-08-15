package com.bulkup.health.entity.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("ROLE_TRAINER")
public class Trainer extends Account {
    @Column(name = "price_per", nullable = false)
    private Integer pricePer;

    @Column(name = "gym_code", nullable = false)
    private String gymCode;

    @Column(name = "verified", nullable = false)
    private Boolean verified;
}
