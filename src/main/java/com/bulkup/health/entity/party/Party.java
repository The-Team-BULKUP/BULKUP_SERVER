package com.bulkup.health.entity.party;

import javax.persistence.*;
import java.time.Instant;

@Entity
@DiscriminatorColumn(name = "dtype")
@Inheritance(strategy = InheritanceType.JOINED) // 조인전략
public abstract class Party {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "crew_leader_id", nullable = false, length = 45)
    private String crewLeaderId;

    @Column(name = "trainer_id")
    private Long trainerId;

    @Column(name = "preferred_price", nullable = false)
    private Integer preferredPrice;

    @Column(name = "preferred_how_many", nullable = false)
    private Integer preferredHowMany;

    @Column(name = "preferred_day", nullable = false)
    private Boolean preferredDay = false;

    @Column(name = "preferred_time")
    private Boolean preferredTime;

    @Column(name = "preferred_distance", nullable = false)
    private Double preferredDistance;

    @Column(name = "base_latitude", nullable = false)
    private Double baseLatitude;

    @Column(name = "base_longitude", nullable = false)
    private Double baseLongitude;

    @Column(name = "create_at", nullable = false)
    private Instant createAt;

}