package com.bulkup.health.controller;

import com.bulkup.health.config.CurrentUserParameter;
import com.bulkup.health.config.exception.CustomException;
import com.bulkup.health.dto.ChatDto;
import com.bulkup.health.entity.account.Account;
import com.bulkup.health.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat")
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/rooms")
    public ChatDto.Response.GetRoomsByAccount getRoomsByAccount(@CurrentUserParameter Account account) {
        // TODO: if account is null, need to handling
        return chatService.getRoomsByAccount(account);
    }

    @PostMapping("/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoom(@RequestBody ChatDto.Request.CreateRoom req) {
        chatService.createRoom(req);
    }

    @GetMapping("/rooms/{id}/chats")
    public void getChatsByRoom() {

    }

    @PostMapping("/rooms/{id}/chats")
    public void sendChatByRoom() {

    }
}
