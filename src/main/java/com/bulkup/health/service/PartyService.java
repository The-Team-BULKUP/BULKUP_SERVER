package com.bulkup.health.service;

import com.bulkup.health.config.exception.CustomException;
import com.bulkup.health.config.exception.ErrorCode;
import com.bulkup.health.dto.PartyDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.entity.party.PartyAlone;
import com.bulkup.health.entity.party.PartyCrew;
import com.bulkup.health.entity.party.PartyMember;
import com.bulkup.health.repository.party.PartyAloneRepository;
import com.bulkup.health.repository.party.PartyCrewRepository;
import com.bulkup.health.repository.party.PartyMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartyService {
    private final PartyCrewRepository partyCrewRepository;
    private final PartyAloneRepository partyAloneRepository;
    private final PartyMemberRepository partyMemberRepository;
    @Transactional
    public void createParty(Account account, PartyDto.Request.CreateParty request, String partyType) {
        if (account == null || !account.isUser())
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);

        // 파티 생성
        Long partyId = null;
        if (partyType.equals("alone")) {
            PartyAlone partyAlone = request.toPartyAlone();
            partyAlone.setCrewLeaderId(account.getId());
            partyId = partyAloneRepository.save(partyAlone).getId();
        } else if (partyType.equals("crew")) {
            PartyCrew partyCrew = request.toPartyCrew();
            partyCrew.setCrewLeaderId(account.getId());
            partyId = partyCrewRepository.save(partyCrew).getId();
        }

        if (partyId == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);

        // 파티 멤버 초기화 생성
        PartyMember partyMember = new PartyMember();
        partyMember.setMemberId(account.getId());
        partyMember.setPartyId(partyId);
        partyMemberRepository.save(partyMember);
    }
}
