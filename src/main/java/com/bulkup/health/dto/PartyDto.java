package com.bulkup.health.dto;

import com.bulkup.health.entity.party.PartyAlone;
import com.bulkup.health.entity.party.PartyCrew;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PartyDto {
    public static class Response {
        @Getter
        @Builder
        @AllArgsConstructor
        public static class PartyInfo {
            private String name;
            private AccountDto.Response.User leader;
            private Long trainerId;
            private Integer preferredHowMany;
            private String preferredDay;
            private String preferredTime;
            private Double distance;
            private String discriminatorValue;
            private Point point;
            private Long preferredPrice;
        }

        @Getter
        @AllArgsConstructor
        public static class Point{
            private Double lat;
            private Double lng;
        }

    }
    public static class Request {

        @Data
        public static class SearchParty{
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

        @Data
        public static class CreateParty{
            @NotNull(message = "크루 이름을 입력하세요.")
            private String name;

            @NotNull(message = "선호 가격을 입력하세요.")
            @Min(value = 0, message = "선호 가격은 0 이상이어야 합니다.")
            private Long preferredPrice;

            @NotNull(message = "선호 횟수를 입력하세요.")
            @Min(value = 1, message = "선호 횟수는 1 이상이어야 합니다.")
            private Integer preferredHowMany;

            @NotNull(message = "선호 요일을 입력하세요.")
            @Size(min = 7, max = 7, message = "선호 요일을 제대로 입력해주세요.")
            private String preferredDay;

            @NotNull(message = "선호 시간을 입력하세요.")
            @Size(min = 24, max = 24, message = "선호 시간을 제대로 입력해주세요.")
            private String preferredTime;

            @NotNull(message = "선호 거리를 입력하세요.")
            @Min(value = 0, message = "선호 거리를 제대로 입력해주세요.")
            private Double preferredDistance;

            @NotNull(message = "선호 위치의 위도를 입력하세요.")
            private Double baseLatitude;

            @NotNull(message = "선호 위치의 경도를 입력하세요.")
            private Double baseLongitude;

            public PartyAlone toPartyAlone(){
                return PartyAlone.builder()
                        .name(name)
                        .preferredPrice(preferredPrice)
                        .preferredHowMany(preferredHowMany)
                        .preferredDay(preferredDay)
                        .preferredTime(preferredTime)
                        .preferredDistance(preferredDistance)
                        .baseLatitude(baseLatitude)
                        .baseLongitude(baseLongitude)
                        .build();
            }

            public PartyCrew toPartyCrew(){
                return PartyCrew.builder()
                        .name(name)
                        .preferredPrice(preferredPrice)
                        .preferredHowMany(preferredHowMany)
                        .preferredDay(preferredDay)
                        .preferredTime(preferredTime)
                        .preferredDistance(preferredDistance)
                        .baseLatitude(baseLatitude)
                        .baseLongitude(baseLongitude)
                        .build();
            }
        }

    }
}
