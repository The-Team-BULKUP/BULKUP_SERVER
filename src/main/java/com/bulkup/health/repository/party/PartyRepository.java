package com.bulkup.health.repository.party;

import com.bulkup.health.entity.party.Party;
import com.bulkup.health.entity.party.PartyCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartyRepository extends JpaRepository<Party, Long> {

    // todo: Spatial Index로 성능 최적화 하기 -hun
    @Query(value = "SELECT *, " +
            "ST_Distance_Sphere(POINT(:lng, :lat), POINT(base_longitude, base_latitude)) AS 'calculated_distance' " +
            "FROM party " +
            "WHERE ST_Distance_Sphere(POINT(:lng, :lat), POINT(base_longitude, base_latitude)) <= :dist " +
            "       AND party_type='crew' " +
            "       AND trainer_id IS NULL ", nativeQuery = true)
    List<PartyCrew> searchPartyCrewByDistance(double lng, double lat, double dist);
}
