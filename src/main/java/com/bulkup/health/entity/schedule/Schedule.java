package com.bulkup.health.entity.schedule;

import javax.persistence.*;
import java.util.BitSet;

@Entity
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.JOINED) // 조인전략
public class Schedule {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "yyyymmdd", nullable = false)
    private Integer yyyymmdd;

    @Column(name = "time", nullable = false)
    private BitSet time;

}