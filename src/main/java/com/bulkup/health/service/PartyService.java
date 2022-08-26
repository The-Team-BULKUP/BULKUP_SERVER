package com.bulkup.health.service;

import com.bulkup.health.config.exception.CustomException;
import com.bulkup.health.config.exception.ErrorCode;
import com.bulkup.health.dto.AccountDto;
import com.bulkup.health.dto.PartyDto;
import com.bulkup.health.dto.PartyMemberDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.entity.account.Trainer;
import com.bulkup.health.entity.party.Party;
import com.bulkup.health.entity.party.PartyAlone;
import com.bulkup.health.entity.party.PartyCrew;
import com.bulkup.health.entity.party.PartyMember;
import com.bulkup.health.repository.account.TrainerRepository;
import com.bulkup.health.repository.account.UserRepository;
import com.bulkup.health.repository.party.PartyAloneRepository;
import com.bulkup.health.repository.party.PartyCrewRepository;
import com.bulkup.health.repository.party.PartyMemberRepository;
import com.bulkup.health.repository.party.PartyRepository;
import com.bulkup.health.util.TelegramAlertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Base64;
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
    private final TrainerRepository trainerRepository;
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

    public PartyDto.Response.GetMyPartyList getMyPartyIn(Account account) {
    // 내가 참가자로 속한 파티 조회 메서드
        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);

        PartyDto.Response.GetMyPartyList response = new PartyDto.Response.GetMyPartyList();
        List<Party> partyList_temp = partyRepository.getPartyByParticipantId(account.getId());
        partyList_temp.forEach(
                party -> {
                    Trainer trainer = (party.getTrainerId() != null) ? trainerRepository.findById(party.getTrainerId()).orElse(null) : null;
                    PartyMemberDto.Response.Trainer trainerInfo = null;
                    if (trainer != null){
                        trainerInfo =
                                PartyMemberDto.Response.Trainer.builder()
                                .id(trainer.getId())
                                .realName(trainer.getRealName())
                                .username(trainer.getUsername())
                                .profileImg(Base64.getEncoder().encodeToString(trainer.getProfileImg()))
                                .gym(new PartyMemberDto.Response.Gym(trainer.getGymLat(), trainer.getGymLng(), trainer.getGymName(), trainer.getGymPhoto()))
                                .introduce(trainer.getIntroduce())
                                .pricePer(trainer.getPricePer())
                                .build();
                    }
                    response.addList(
                            PartyDto.Response.GetMyParty.builder()
                                    .id(party.getId())
                                    .trainer(trainerInfo)
                                    .partyType(party.getDiscriminatorValue())
                                    .preferredDay(party.getPreferredDay())
                                    .preferredPrice(party.getPreferredPrice())
                                    .preferredHowMany(party.getPreferredHowMany())
                                    .preferredTime(party.getPreferredTime())
                                    .name(party.getName())
                                    .description(party.getDescription())
                                    .hostByMe(false)
                                    .build()
                    );
                }
        );
        return response;
    }

    public List<PartyDto.Response.PartyInfo> searchPartyCrew(Account account, PartyDto.Request.SearchParty request) {
        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);

        List<PartyDto.Response.PartyInfo> response = new ArrayList<>();
        partyRepository.searchPartyCrewByDistance(request.getLng(), request.getLat(), request.getDistance()).stream()
                // 본인이 만든 파티는 검색 결과에서 제외
                .filter(party -> !party.getLeaderIdx().equals(account.getId()))
                .forEach(
                        party -> {
                            PartyDto.Response.PartyInfo partyInfo =
                                    PartyDto.Response.PartyInfo.builder()
                                            .id(party.getId())
                                            .name(party.getName())
                                            .description(party.getDescription())
                                            .leader(new AccountDto.Response.User(party.getId(), party.getLeaderUsername(), party.getLeaderNickname()))
                                            .preferredTime(party.getPreferredTime())
                                            .preferredDay(party.getPreferredDay())
                                            .preferredHowMany(party.getPreferredHowMany())
                                            .preferredPrice(party.getPreferredPrice())
                                            .distance(party.getCalculatedDistance())
                                            .point(new PartyDto.Response.Point(party.getLat(), party.getLng()))
                                            .preferredDistance(party.getPreferredDistance())
                                            .memberCount(party.getCurrentMemberCount())
                                            .type(party.getType())
                                            .build();
                            response.add(partyInfo);
                        }
                );
        return response;
    }
    @Transactional
    public void joinParty(Account account, Long partyId) {
        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        Party party = partyRepository.findById(partyId)
                .orElseThrow(() -> new CustomException(ErrorCode.HANDLE_ACCESS_DENIED));
        if (account.isUser() && !Objects.equals(party.getDiscriminatorValue(), "crew"))
            throw new CustomException(ErrorCode.ONLY_ACCESS_CREW);

        partyMemberRepository.findByPartyIdAndAccountId(partyId, account.getId())
                .ifPresent(partyMember -> {
                    throw new CustomException(ErrorCode.ALREADY_JOINED);
                });

        if (account.isUser()) {
            PartyMember partyMember = new PartyMember();
            partyMember.setMemberId(account.getId());
            partyMember.setPartyId(partyId);
            partyMemberRepository.save(partyMember);

            TelegramAlertUtil.sendAlert("새로운 참가자가 파티에 참가했습니다.\n파티명: " + party.getName() + ", 파티 id값 : "
                    + party.getId() + "참가자 id값 : " + account.getId());
        }
        else if (account.isTrainer())
            TelegramAlertUtil.sendAlert("트레이너가 파티에 문의요청 했습니다.\n파티명: " + party.getName() + ", 파티 id값 : "
                    + party.getId() +  "트레이너 id값 : " + account.getId());
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

    public List<PartyDto.Response.PartyInfo> findPartyForTrainer(Account account) {

        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);

        Trainer trainer = trainerRepository.findById(account.getId())
                .orElseThrow(() -> new CustomException(ErrorCode.HANDLE_ACCESS_DENIED));

        double gymLat = trainer.getGymLat();
        double gymLng = trainer.getGymLng();
        List<PartyDto.Response.PartyInfo> response = new ArrayList<>();
        partyRepository.searchPartyForTrainer(gymLng, gymLat).stream()
                .forEach(
                        party -> {
                            PartyDto.Response.PartyInfo partyInfo =
                                    PartyDto.Response.PartyInfo.builder()
                                            .id(party.getId())
                                            .name(party.getName())
                                            .description(party.getDescription())
                                            .leader(new AccountDto.Response.User(party.getId(), party.getLeaderUsername(), party.getLeaderNickname()))
                                            .preferredTime(party.getPreferredTime())
                                            .preferredDay(party.getPreferredDay())
                                            .preferredHowMany(party.getPreferredHowMany())
                                            .preferredPrice(party.getPreferredPrice())
                                            .distance(party.getCalculatedDistance())
                                            .point(new PartyDto.Response.Point(party.getLat(), party.getLng()))
                                            .memberCount(party.getCurrentMemberCount())
                                            .preferredDistance(party.getPreferredDistance())
                                            .type(party.getType())
                                            .build();
                            response.add(partyInfo);
                        }
                );
        return response;

    }
}
