package com.bulkup.health.repository.community;

import com.bulkup.health.entity.community.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
