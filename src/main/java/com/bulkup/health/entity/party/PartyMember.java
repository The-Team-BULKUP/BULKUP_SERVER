package com.bulkup.health.entity.party;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }
    public void setMemberId(Long memberId) {
        this.accountId = memberId;
    }
}