package com.bulkup.health.dto;

public interface TrainerInformation {

    Long getId();
    String getUsername();
    String getRealName();
    Integer getPricePer();
    byte[] getProfileImg();
    String getIntroduce();

    String getPhone();
    String getGymName();
    String getGymPhoto();
    Double getGymLat();
    Double getGymLng();
    Double getCalculatedDistance();
}
