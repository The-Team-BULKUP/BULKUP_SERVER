package com.bulkup.health.entity.account;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ROLE_Trainer")
public class Trainer extends Account {
    @Column(name = "price_per")
    private Integer pricePer;

}
