package com.bulkup.health.dto;

import java.time.LocalDateTime;

public interface ScheduleInformation {
    Long getId();
    String getName();
    Long getCrewId();
    Long getTrainerId();
    LocalDateTime getStart();
    LocalDateTime getEnd();
    String getType();
}
