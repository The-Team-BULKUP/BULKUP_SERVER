package com.bulkup.health.entity.party;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(
        name = "party_type",
        discriminatorType = DiscriminatorType.STRING,
        columnDefinition = "VARCHAR(5)")
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Party {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "crew_leader_id", nullable = false, length = 45)
    private Long crewLeaderId;

    @Column(name = "trainer_id")
    private Long trainerId;

    @Column(name = "preferred_price", nullable = false)
    private Long preferredPrice;

    @Column(name = "preferred_how_many", nullable = false)
    private String preferredHowMany;

    @Column(name = "preferred_day", nullable = false)
    private String preferredDay;

    @Column(name = "preferred_time")
    private String preferredTime;

    @Column(name = "preferred_distance", nullable = false)
    private Double preferredDistance;

    @Column(name = "base_latitude", nullable = false)
    private Double baseLatitude;

    @Column(name = "base_longitude", nullable = false)
    private Double baseLongitude;

    @CreatedDate
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Transient
    public String getDiscriminatorValue(){
        //for tests
        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? null : val.value();
    }
    public void setCrewLeaderId(Long crewLeaderId) {
        this.crewLeaderId = crewLeaderId;
    }
}