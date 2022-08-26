package com.bulkup.health.dto;

import com.bulkup.health.entity.schedule.PartySchedule;
import com.bulkup.health.entity.schedule.TrainerExtraSchedule;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class ScheduleDto {

    public static class Response {

        @Data
        @Builder
        public static class Schedule{
            private Long id;
            private String name;
//            private Long crewId;
//            private Long trainerId;
            private LocalDateTime start;
            private LocalDateTime end;
            private String type;
            private Object details;
        }
    }


    public static class Request {
        @Data
        public static class CreatePartySchedule {
            @NotBlank(message = "스케줄 이름을 입력해주세요.")
            private String name;
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
            @NotBlank(message = "스케줄 시작 시간을 입력해주세요.")
            private LocalDateTime start;
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
            @NotBlank(message = "스케줄 종료 시간을 입력해주세요.")
            private LocalDateTime end;

            public PartySchedule toEntity(){
                return PartySchedule.builder()
                        .name(name)
                        .start(start)
                        .end(end)
                        .build();
            }
        }
        @Data
        public static class CreateTrainerExtraSchedule {
            @NotBlank(message = "스케줄 이름을 입력해주세요.")
            private String name;
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
            @NotBlank(message = "스케줄 시작 시간을 입력해주세요.")
            private LocalDateTime start;
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
            @NotBlank(message = "스케줄 종료 시간을 입력해주세요.")
            private LocalDateTime end;

            public TrainerExtraSchedule toEntity(){
                return TrainerExtraSchedule.builder()
                        .name(name)
                        .start(start)
                        .end(end)
                        .build();
            }
        }
    }
}
