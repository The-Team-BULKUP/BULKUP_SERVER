package com.bulkup.health.entity.party;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Party_member")
public class PartyMember {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "crew_id", nullable = false)
    private Long crewId;

    @Column(name = "account_id", nullable = false)
    private Long accountId;
}