package com.bulkup.health.entity.party;

import javax.persistence.*;

@Entity
@Table(name = "Party_member", schema = "bulkup")
public class PartyMember {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crew_id", nullable = false)
    private Long crewId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;
}