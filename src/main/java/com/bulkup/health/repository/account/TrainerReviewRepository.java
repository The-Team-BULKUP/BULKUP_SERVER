package com.bulkup.health.repository.account;

import com.bulkup.health.entity.account.TrainerReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerReviewRepository extends JpaRepository<TrainerReview, Long> {

}
