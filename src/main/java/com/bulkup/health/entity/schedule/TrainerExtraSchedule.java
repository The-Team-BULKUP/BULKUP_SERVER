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
@DiscriminatorValue("extra")
public class TrainerExtraSchedule extends Schedule {
    @Column(name = "trainer_id")
    private Long trainerId;

}
