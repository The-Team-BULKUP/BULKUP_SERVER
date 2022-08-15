package com.bulkup.health.entity.party;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("alone")
public class PartyAlone extends Party{

}
