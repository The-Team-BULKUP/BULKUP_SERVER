package com.bulkup.health.entity.party;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("crew")
public class PartyCrew extends Party{

}
