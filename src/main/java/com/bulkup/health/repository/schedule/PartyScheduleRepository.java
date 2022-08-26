package com.bulkup.health.repository.schedule;

import com.bulkup.health.dto.ScheduleInformation;
import com.bulkup.health.entity.schedule.PartySchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartyScheduleRepository extends JpaRepository<PartySchedule, Long> {
    @Query(value = "" +
            "SELECT id, name, crew_id as crewId, trainer_id as trainerId, start, end, schedule_type as type " +
            "FROM schedule s " +
            "WHERE s.trainer_id = :trainerId " +
            "order by s.start ", nativeQuery = true)
    List<ScheduleInformation> getTrainerSchedule(Long trainerId);

    List<PartySchedule> getPartyScheduleByCrewId(Long crewId);

}
