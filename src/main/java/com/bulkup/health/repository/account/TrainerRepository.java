package com.bulkup.health.repository.account;

import com.bulkup.health.dto.TrainerInformation;
import com.bulkup.health.entity.account.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    @Query(value = "" +
            "SELECT a.id, a.username, a.real_name as realName, a.nickname, a.phone, a.role, a.introduce, a.price_per as pricePer, " +
            " a.activated, a.gym_lat as gymLat , a.gym_lng as gymLng, a.profile_img as profileImg, " +
            " a.gym_name as gymName, a.gym_photo as gymPhoto, " +
            " ST_Distance_Sphere(POINT(:lng, :lat), POINT(gym_lng, gym_lat)) AS calculatedDistance " +
            "FROM  account as a " +
            "WHERE  ST_Distance_Sphere(POINT(:lng, :lat), POINT(gym_lng, gym_lat)) <= :dist " +
            "       AND activated = true " +
            "       AND a.role = 'ROLE_TRAINER'" +
            " order by calculatedDistance", nativeQuery = true)
    List<TrainerInformation> searchTrainerByDistance(double lng, double lat, double dist);
}
