package com.bulkup.health.dto;

public interface PartyInformation {
    Long getId();
    String getName();
    Long getLeaderIdx();
    String getLeaderUsername();
    String getLeaderNickname();
    Long getTrainerId();
    Integer getPreferredHowMany();
    String getPreferredDay();
    String getPreferredTime();
    Double getCalculatedDistance();
    Double getLat();
    Double getLng();
    Long getPreferredPrice();
    String getType();
}
