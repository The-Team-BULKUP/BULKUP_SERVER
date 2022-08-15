package com.bulkup.health.entity.community;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Entity
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "writer", nullable = false)
    private Long writer;

    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @Column(name = "content", nullable = false, length = 300)
    private String content;

    @CreatedDate
    @Column(name = "create_at", nullable = false)
    private Instant createAt;

    @LastModifiedDate
    @Column(name = "modify_at")
    private Instant modifyAt;

}