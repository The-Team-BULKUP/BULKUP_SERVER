package com.bulkup.health.service;

import com.bulkup.health.dto.PartyMemberDto;
import com.bulkup.health.dto.TrainerDto;
import com.bulkup.health.dto.TrainerInformation;
import com.bulkup.health.repository.account.TrainerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerService {
    private final TrainerRepository trainerRepository;
    public TrainerDto.Response.TrainerList getTrainerListByPointMapping(TrainerDto.Request.TrainerSearch req) {
        List<TrainerInformation> trainerList = trainerRepository.searchTrainerByDistance(req.getLng(), req.getLat(), req.getDistance());
        TrainerDto.Response.TrainerList response = new TrainerDto.Response.TrainerList();

        trainerList.forEach(
                trainer -> {
                    PartyMemberDto.Response.Trainer trainerResponse = PartyMemberDto.Response.Trainer.builder()
                            .id(trainer.getId())
                            .gym(PartyMemberDto.Response.Gym.builder()
                                    .gymName(trainer.getGymName())
                                    .gymPhoto(trainer.getGymPhoto())
                                    .lat(trainer.getGymLat())
                                    .lng(trainer.getGymLng())
                                    .build())
                            .realName(trainer.getRealName())
                            .profileImg(Base64.getEncoder().encodeToString(trainer.getProfileImg()))
                            .introduce(trainer.getIntroduce())
                            .pricePer(trainer.getPricePer())
                            .username(trainer.getUsername())
                            .distance(trainer.getCalculatedDistance())
                            .build();
                    response.add(trainerResponse);
                }
        );
        return response;
    }

}
