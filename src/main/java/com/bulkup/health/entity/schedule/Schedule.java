package com.bulkup.health.entity.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.BitSet;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(
        name = "schedule_type",
        discriminatorType = DiscriminatorType.STRING,
        columnDefinition = "VARCHAR(10)")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Schedule {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "yyyymmdd", nullable = false)
    private Integer yyyymmdd;

    @Column(name = "time", nullable = false)
    private BitSet time;

    @Transient
    public String getDiscriminatorValue(){
        //for tests
        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? null : val.value();
    }
}