package com.bulkup.health.repository.party;

import com.bulkup.health.dto.PartyInformation;
import com.bulkup.health.entity.party.Party;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {

    // todo: Spatial Index로 성능 최적화 하기 -hun
    @Query(value = "SELECT party.id as id, party.description as description, party.name as name , party.crew_leader_id as leaderIdx, " +
            " party.preferred_day as preferredDay, party.preferred_distance as preferredDistance, " +
            " party.preferred_how_many as preferredHowMany, party.preferred_price as preferredPrice, party.preferred_time as preferredTime, " +
            " party.base_latitude as lat, party.base_longitude as lng, party.party_type as type, party.create_at as createAt, " +
            " a.username as leaderUsername, a.nickname as leaderNickname, " +
            " IFNULL(pm.CNT, 0) + 1 AS currentMemberCount, " +
            " ST_Distance_Sphere(POINT(:lng, :lat), POINT(base_longitude, base_latitude)) AS calculatedDistance " +
            "FROM party " +
            "JOIN account a ON a.id = party.crew_leader_id " +
            "LEFT OUTER JOIN ( SELECT party_member.crew_id, party_member.account_id, COUNT(*) AS CNT " +
            "FROM party_member " +
            "GROUP BY party_member.crew_id) as pm " +
            "ON party.id = pm.crew_id " +
            "WHERE ST_Distance_Sphere(POINT(:lng, :lat), POINT(base_longitude, base_latitude)) <= :dist " +
            "       AND party.party_type='crew' " +
            "       AND party.trainer_id IS NULL " +
            " order by calculatedDistance", nativeQuery = true)
    List<PartyInformation> searchPartyCrewByDistance(double lng, double lat, double dist);

}