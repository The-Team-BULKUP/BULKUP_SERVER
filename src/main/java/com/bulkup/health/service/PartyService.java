package com.bulkup.health.service;

import com.bulkup.health.config.exception.CustomException;
import com.bulkup.health.config.exception.ErrorCode;
import com.bulkup.health.dto.AccountDto;
import com.bulkup.health.dto.PartyDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.entity.account.User;
import com.bulkup.health.entity.party.Party;
import com.bulkup.health.entity.party.PartyAlone;
import com.bulkup.health.entity.party.PartyCrew;
import com.bulkup.health.entity.party.PartyMember;
import com.bulkup.health.repository.account.UserRepository;
import com.bulkup.health.repository.party.PartyAloneRepository;
import com.bulkup.health.repository.party.PartyCrewRepository;
import com.bulkup.health.repository.party.PartyMemberRepository;
import com.bulkup.health.repository.party.PartyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PartyService {
    private final PartyCrewRepository partyCrewRepository;
    private final PartyAloneRepository partyAloneRepository;
    private final PartyRepository partyRepository;
    private final PartyMemberRepository partyMemberRepository;
    private final UserRepository userRepository;
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

    @Transactional
    public void deleteParty(Account account, Long partyId){
        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new CustomException(ErrorCode.HANDLE_ACCESS_DENIED));

        if (party.getCrewLeaderId().equals(account.getId())) {
            // 본인의 파티만 삭제 가능
            partyRepository.delete(party);
        } else {
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
    }

    public List<PartyDto.Response.PartyInfo> searchPartyCrew(Account account, PartyDto.Request.SearchParty request) {
        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);

        List<PartyCrew> results = partyRepository.searchPartyCrewByDistance(request.getLng(), request.getLat(), request.getDistance());
        List<PartyDto.Response.PartyInfo> response = new ArrayList<>();
        results.stream()
                // 본인이 만든 파티는 검색 결과에서 제외
                .filter(partyCrew -> !Objects.equals(partyCrew.getCrewLeaderId(), account.getId()))
                .forEach(party -> {
                    User crewLeader = userRepository.findById(party.getCrewLeaderId())
                                    .orElseThrow(() -> new CustomException(ErrorCode.HANDLE_ACCESS_DENIED));
                    PartyDto.Response.PartyInfo partyInfo =
                            PartyDto.Response.PartyInfo.builder()
                                    .name(party.getName())
                                    .leader(new AccountDto.Response.User(crewLeader.getUsername(), crewLeader.getNickname()))
                                    .preferredTime(party.getPreferredTime())
                                    .preferredDay(party.getPreferredDay())
                                    .preferredHowMany(party.getPreferredHowMany())
                                    .discriminatorValue(party.getDiscriminatorValue())
                                    .build();
                    response.add(partyInfo);
                });
        return response;
    }
    @Transactional
    public void joinParty(Account account, Long partyId) {
        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new CustomException(ErrorCode.HANDLE_ACCESS_DENIED));
        if (!party.getDiscriminatorValue().equals("crew"))
            throw new CustomException(ErrorCode.ONLY_ACCESS_CREW);

        if (account.isTrainer() || account.isUser()){
            partyMemberRepository.findByPartyIdAndAccountId(partyId, account.getId())
                    .ifPresent(partyMember -> {
                        throw new CustomException(ErrorCode.ALREADY_JOINED);
                    });
            PartyMember partyMember = new PartyMember();
            partyMember.setMemberId(account.getId());
            partyMember.setPartyId(partyId);
            partyMemberRepository.save(partyMember);
        } else {
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        }
    }
    @Transactional
    public void registerPartyTrainer(Account account, Long partyId){
        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        if (!account.isTrainer())
            throw new CustomException(ErrorCode.ONLY_ACCESS_TRAINER);
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new CustomException(ErrorCode.HANDLE_ACCESS_DENIED));

        if (party.getTrainerId() == null) {
            party.setTrainerId(account.getId());
            partyRepository.save(party);
        } else throw new CustomException(ErrorCode.PARTY_TRAINER_ALREADY_EXIST);
    }
}
