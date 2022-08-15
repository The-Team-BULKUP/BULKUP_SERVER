package com.bulkup.health.entity.schedule;

import javax.persistence.*;
import java.util.BitSet;

@Entity
@DiscriminatorColumn(
        name = "schedule_type",
        discriminatorType = DiscriminatorType.STRING,
        columnDefinition = "VARCHAR(10)"
)
@Inheritance(strategy = InheritanceType.JOINED) // 조인전략
public class Schedule {
    @Id
    @Column(name = "id", nullable = false)
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