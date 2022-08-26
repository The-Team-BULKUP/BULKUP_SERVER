package com.bulkup.health.dto;

import com.bulkup.health.entity.community.Board;
import com.bulkup.health.entity.community.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {
    public static class Request {
        @Data
        public static class CreatePost {
            private String title;
            private String content;

            public Board toEntity(Long writer) {
                return Board.builder()
                        .title(title)
                        .content(content)
                        .writer(writer)
                        .build();
            }
        }

        @Data
        public static class CreateComment {
            private String content;

            public Comment toEntity(Long writer, Long boardId) {
                return Comment.builder()
                        .boardId(boardId)
                        .accountIdx(writer)
                        .content(content)
                        .build();
            }
        }
    }

    public static class Response {
        @Getter
        @AllArgsConstructor
        public static class GetPosts {
            private List<Board> posts;
            private int count;
        }

        @Getter
        @AllArgsConstructor
        public static class GetPostById {
            private String title;
            private String content;
            private Long writer;
            private List<Comment> comments;
        }

        @Getter
        @AllArgsConstructor
        public static class GetComments {
            private String content;
            private Long accountId;
            private LocalDateTime create_at;
        }
    }
}
