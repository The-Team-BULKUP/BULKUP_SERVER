package com.bulkup.health.entity.party;

import javax.persistence.*;

@Entity
@Table(name = "party_member", schema = "bulkup")
public class PartyMember {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "crew_id")
    private Long partyId;

    @Column(name = "account_id")
    private Long accountId;

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
    public void setMemberId(Long memberId) {
        this.accountId = memberId;
    }
}