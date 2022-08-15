package com.bulkup.health.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {
    private String token;
    private String tokenExpired;
    private String refresh;
//    private SecurityRole securityRole;
}