package com.bulkup.health.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public abstract class GymListDto {

    @JsonProperty("result")
    private Result result;
    @JsonProperty("code")
    private int code;

    public static class Result {
        @JsonProperty("total")
        private int total;
        @JsonProperty("centerList")
        private List<CenterList> centerList;
        @JsonProperty("promotionalCenterList")
        private List<String> promotionalCenterList;
    }

    public static class CenterList {
        @JsonProperty("dagymProgramList")
        private List<String> dagymProgramList;
        @JsonProperty("isReservationRequired")
        private boolean isReservationRequired;
        @JsonProperty("isWomenOnly")
        private boolean isWomenOnly;
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
