package com.bulkup.health.entity.schedule;

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
@DiscriminatorValue("party")
public class PartySchedule extends Schedule {
    @Column(name = "crew_id")
    private Long crewId;
    @Column(name = "trainer_id")
    private Long trainerId;
    public void setTrainerId(Long trainerId) {
        this.trainerId = trainerId;
    }

    public void setCrewId(Long crewId) {
        this.crewId = crewId;
    }
}
