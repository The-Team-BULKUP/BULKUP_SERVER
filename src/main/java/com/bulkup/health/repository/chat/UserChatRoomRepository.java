package com.bulkup.health.repository.chat;

import com.bulkup.health.entity.UserChatRoom;
import com.bulkup.health.entity.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserChatRoomRepository extends JpaRepository<UserChatRoom, Long> {
    @Query(value = "select * from user_chat_room where account_id = :accountId", nativeQuery = true)
    Optional<List<UserChatRoom>> findByAccount(Long accountId);
}
