package com.bulkup.health.entity.schedule;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("extra")
public class TrainerExtraSchedule extends Schedule {
    @Column(name = "trainer_id")
    private Long trainerId;

}
