package com.bulkup.health.entity.community;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Entity
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "board_id", nullable = false)
    private Long boardId;

    @Column(name = "account_idx", nullable = false)
    private Long accountIdx;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @CreatedDate
    @Column(name = "create_at", nullable = false)
    private Instant createAt;

}