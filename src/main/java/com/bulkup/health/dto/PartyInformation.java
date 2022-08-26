package com.bulkup.health.dto;

public interface PartyInformation {

    Long getId();
    String getName();
    String getDescription();
    Long getLeaderIdx();
    String getLeaderUsername();
    String getLeaderNickname();
    Long getTrainerId();
    String getTrainerRealName();
    Double getPreferredDistance();

    Integer getPreferredHowMany();
    String getPreferredDay();
    String getPreferredTime();
    Double getCalculatedDistance();
    Double getLat();
    Double getLng();
    Long getPreferredPrice();
    String getType();
    Integer getCurrentMemberCount();
}
