package com.bulkup.health.entity.account;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Trainer_review", schema = "bulkup")
public class TrainerReview {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trainer_id", nullable = false)
    private Long trainerId;

    @Column(name = "reviewer_id", nullable = false, length = 45)
    private String reviewerId;

    @Column(name = "score", nullable = false)
    private Byte score;

    @Column(name = "review", nullable = false, length = 100)
    private String review;

    @CreatedDate
    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;
}