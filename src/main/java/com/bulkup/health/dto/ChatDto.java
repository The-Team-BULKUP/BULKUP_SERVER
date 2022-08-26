package com.bulkup.health.dto;

import com.bulkup.health.entity.ChatRoom;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class ChatDto {

    public static class Request {
        @Getter
        @AllArgsConstructor
        public static class CreateRoom {
            private String title;
            private List<Long> accounts;

            public ChatRoom toEntity() {
                return ChatRoom.builder()
                        .title(title)
                        .build();
            }
        }

        @Getter
        @RequiredArgsConstructor
        public static class GetRoomsByAccount {
            private Long id;
        }
    }

    public static class Response {
        @Getter
        @AllArgsConstructor
        public static class GetRoomsByAccount {
            private List<ChatRoom> rooms;
        }
    }
}
