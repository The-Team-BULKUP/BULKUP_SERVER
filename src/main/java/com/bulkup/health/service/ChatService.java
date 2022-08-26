package com.bulkup.health.service;

import com.bulkup.health.dto.ChatDto;
import com.bulkup.health.entity.ChatRoom;
import com.bulkup.health.entity.UserChatRoom;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.repository.account.AccountRepository;
import com.bulkup.health.repository.chat.ChatRepository;
import com.bulkup.health.repository.chat.ChatRoomRepository;
import com.bulkup.health.repository.chat.UserChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional()
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserChatRoomRepository userChatRoomRepository;
    private final AccountRepository accountRepository;

    @Transactional()
    public void createRoom(ChatDto.Request.CreateRoom req) {
        log.info(req.getTitle() + " " + req.getAccounts() + "hi");
        ChatRoom chatRoom = chatRoomRepository.save(req.toEntity());
        for (long id : req.getAccounts()) {
            Account a = accountRepository.findById(id).orElse(null);
            if (a == null) break;
            userChatRoomRepository.save(
                    UserChatRoom
                            .builder()
                            .room(chatRoom)
                            .account(a)
                            .build()
            );
        }
    }

    public ChatDto.Response.GetRoomsByAccount getRoomsByAccount(Account currentUser) {
        List<ChatRoom> roomList = new ArrayList<>();
        userChatRoomRepository.findByAccount(currentUser.getId()).ifPresent(rooms -> {
            log.info(rooms.toString());
            for (UserChatRoom room : rooms) {
                chatRoomRepository.findById(room.getRoom().getId()).ifPresent(roomList::add);
            }
        });

        return new ChatDto.Response.GetRoomsByAccount(roomList);
    }
}
