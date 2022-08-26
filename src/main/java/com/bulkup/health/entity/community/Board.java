package com.bulkup.health.entity.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Board", schema = "bulkup")
@EntityListeners(AuditingEntityListener.class)
public class Board {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDateTime modifyAt;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private List<Comment> comments;
}
