package com.bulkup.health.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class TrainerDto {

    public static class Response {

        @Data
        public static class TrainerList {

            List<PartyMemberDto.Response.Trainer> trainerList;

            public TrainerList(List<PartyMemberDto.Response.Trainer> trainerList) {
                this.trainerList = trainerList;
            }

            public TrainerList() {
                this.trainerList = new ArrayList<>();
            }

            public void add(PartyMemberDto.Response.Trainer trainer) {
                this.trainerList.add(trainer);
            }
        }

        @Data
        public static class Tariner {
            private Long id;
            private String realName;
            private String introduce;
            private Integer pricePer;
            private String profileImage;
            private String career;
            private PartyMemberDto.Response.Gym gym;
        }

    }

    public static class Request {
        @Data
        public static class TrainerSearch {
            @NotNull(message = "선호 위치의 위도를 설정해주세요.")
            private Double lat;
            @NotNull(message = "선호 위치의 경도를 설정해주세요.")
            private Double lng;
            @NotNull(message = "선호 위치의 반경을 설정해주세요.")
            private Double distance;

            private String preferredHowMany;
            private String preferredDay;
            private String preferredTime;
        }
    }
}
