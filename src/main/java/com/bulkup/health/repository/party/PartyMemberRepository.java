package com.bulkup.health.repository.party;

import com.bulkup.health.entity.party.PartyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartyMemberRepository extends JpaRepository<PartyMember, Long> {

}
