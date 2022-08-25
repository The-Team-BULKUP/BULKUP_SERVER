package com.bulkup.health.repository.party;

import com.bulkup.health.entity.party.PartyMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PartyMemberRepository extends JpaRepository<PartyMember, Long> {
    Optional<PartyMember> findByPartyIdAndAccountId(Long partyId, Long accountId);
    List<PartyMember> findAllByAccountId(Long accountId);

}
