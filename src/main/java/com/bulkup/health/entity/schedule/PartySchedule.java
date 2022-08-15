package com.bulkup.health.entity.schedule;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("party")
public class PartySchedule extends Schedule {
    @Column(name = "crewid")
    private Long crewid;
}
