package com.bulkup.health.service;

import com.bulkup.health.config.exception.CustomException;
import com.bulkup.health.config.exception.ErrorCode;
import com.bulkup.health.dto.ScheduleDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.entity.party.Party;
import com.bulkup.health.entity.party.PartyMember;
import com.bulkup.health.entity.schedule.PartySchedule;
import com.bulkup.health.entity.schedule.TrainerExtraSchedule;
import com.bulkup.health.repository.party.PartyMemberRepository;
import com.bulkup.health.repository.party.PartyRepository;
import com.bulkup.health.repository.schedule.PartyScheduleRepository;
import com.bulkup.health.repository.schedule.TrainerExtraScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final PartyRepository partyRepository;
    private final PartyScheduleRepository partyScheduleRepository;
    private final PartyMemberRepository partyMemberRepository;
    private final TrainerExtraScheduleRepository trainerExtraScheduleRepository;
    @Transactional
    public void createPartySchedule(Account account, ScheduleDto.Request.CreatePartySchedule req, Long partyId) {
        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        if (!account.isTrainer())
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        if (partyId == null)
            throw new CustomException(ErrorCode.HANDLE_INVALID_PARAMETER);
        if (req.getStart().isAfter(req.getEnd()))
            throw new CustomException(ErrorCode.TIME_VALUE_ERROR);

        PartySchedule partySchedule = req.toEntity();
        partySchedule.setCrewId(partyId);
        partySchedule.setTrainerId(account.getId());

        partyScheduleRepository.save(partySchedule);
    }

    @Transactional
    public void createTrainerExtraSchedule(Account account, ScheduleDto.Request.CreateTrainerExtraSchedule req) {
        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        if (!account.isTrainer())
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        if (req.getStart().isAfter(req.getEnd()) || req.getStart().equals(req.getEnd()))
            throw new CustomException(ErrorCode.TIME_VALUE_ERROR);

        TrainerExtraSchedule trainerExtraSchedule = req.toEntity();
        trainerExtraSchedule.setTrainerId(account.getId());
        trainerExtraScheduleRepository.save(trainerExtraSchedule);
    }

    public List<ScheduleDto.Response.Schedule> getTrainerSchedule(Long trainerId) {
        List<ScheduleDto.Response.Schedule> schedules = new ArrayList<>();
        partyScheduleRepository.getTrainerSchedule(trainerId).forEach(
                partySchedule -> {
                    Party details = null;
                    if (Objects.equals(partySchedule.getType(), "party")){
                        details = partyRepository.findById(partySchedule.getCrewId()).orElse(null);
                    }
                    schedules.add(
                            ScheduleDto.Response.Schedule.builder()
                                    .id(partySchedule.getId())
                                    .name(partySchedule.getName())
                                    .start(partySchedule.getStart())
                                    .end(partySchedule.getEnd())
                                    .type(partySchedule.getType())
                                    .details(details)
                                    .build());
                }
        );
        return schedules;
    }

    public List<ScheduleDto.Response.Schedule> getUserSchedule(Account account) {
        if (account == null)
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);
        if (!account.isUser())
            throw new CustomException(ErrorCode.HANDLE_ACCESS_DENIED);

        List<PartyMember> getMyParty1 = partyMemberRepository.findAllByAccountId(account.getId());
        List<Party> getMyParty2 = partyRepository.findAllByCrewLeaderId(account.getId());

        List<ScheduleDto.Response.Schedule> schedules = new ArrayList<>();

        //내가 참가중인 크루
        getMyParty1.forEach(
                party -> {
                    partyScheduleRepository.getPartyScheduleByCrewId(party.getPartyId()).forEach(
                            partySchedule -> {
                                Party details = null;
                                if (Objects.equals(partySchedule.getDiscriminatorValue(), "party")){
                                    details = partyRepository.findById(partySchedule.getCrewId()).orElse(null);
                                }
                                schedules.add(
                                        ScheduleDto.Response.Schedule.builder()
                                                .id(partySchedule.getId())
                                                .name(partySchedule.getName())
                                                .start(partySchedule.getStart())
                                                .end(partySchedule.getEnd())
                                                .type(partySchedule.getDiscriminatorValue())
                                                .details(details)
                                                .build());
                            }
                    );
                }
        );

        // 내가 리더인 크루
        getMyParty2.forEach(
                party -> {
                    partyScheduleRepository.getPartyScheduleByCrewId(party.getId()).forEach(
                            partySchedule -> {
                                Party details = null;
                                if (Objects.equals(partySchedule.getDiscriminatorValue(), "party")){
                                    details = partyRepository.findById(partySchedule.getCrewId()).orElse(null);
                                }
                                schedules.add(
                                        ScheduleDto.Response.Schedule.builder()
                                                .id(partySchedule.getId())
                                                .name(partySchedule.getName())
                                                .start(partySchedule.getStart())
                                                .end(partySchedule.getEnd())
                                                .type(partySchedule.getDiscriminatorValue())
                                                .details(details)
                                                .build());
                            }
                    );
                }
        );
        return schedules;
    }
}