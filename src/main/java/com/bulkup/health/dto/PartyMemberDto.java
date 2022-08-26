package com.bulkup.health.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class PartyMemberDto {

    public static class Response {
        public static class User {
            private Long id;
            private String username;
            private String realName;
            private String nickname;
        }

        @Getter
        @Builder
        @AllArgsConstructor
        public static class Trainer {
            private Long id;
            private String username;
            private String realName;
            private String introduce;
            private Integer pricePer;
            private String profileImg;
            private Double distance;
            private Gym gym;
        }

        @Getter
        public static class Gym extends PartyDto.Response.Point {
            private String gymName;
            private String gymPhoto;
            @Builder
            public Gym(Double lat, Double lng, String gymName, String gymPhoto) {
                super(lat, lng);
                this.gymName = gymName;
                this.gymPhoto = gymPhoto;
            }
        }

    }
    public static class Request {

    }
}
