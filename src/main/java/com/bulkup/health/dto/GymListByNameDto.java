package com.bulkup.health.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class GymListByNameDto {

    @JsonProperty("result")
    private List<Result> result;
    @JsonProperty("code")
    private int code;
    public static class Result {
        @JsonProperty("isReservationRequired")
        private boolean isReservationRequired;
        @JsonProperty("isWomenOnly")
        private boolean isWomenOnly;
        @JsonProperty("isShown")
        private boolean isShown;
        @JsonProperty("tags")
        private List<String> tags;
        @JsonProperty("longitude")
        private double longitude;
        @JsonProperty("latitude")
        private double latitude;
        @JsonProperty("gymPhone")
        private String gymPhone;
        @JsonProperty("address")
        private String address;
        @JsonProperty("gymPhotoSmall")
        private String gymPhotoSmall;
        @JsonProperty("rate")
        private int rate;
        @JsonProperty("gymName")
        private String gymName;
        @JsonProperty("_id")
        private String _id;
    }
}
