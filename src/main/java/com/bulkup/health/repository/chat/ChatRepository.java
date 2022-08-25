package com.bulkup.health.repository.chat;

import com.bulkup.health.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
