package com.bulkup.health.dto;

import com.bulkup.health.config.spring_security.SecurityRole;
import com.bulkup.health.entity.account.Trainer;
import com.bulkup.health.entity.account.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountDto {
    public static class Response {

        @Getter
        @AllArgsConstructor
        public static class Token{
            private String token;
            private String tokenExpired;
            private String refresh;
            private SecurityRole role;
        }
        @Getter
        @AllArgsConstructor
        public static class User {
            private String username;
            private String nickname;
        }

        @Data
        public static class SignupTRAINER {
            private String username;
            @JsonIgnore
            private String password;
            private String realName;
            private int price_per;
            private String gym_code;
            private String introduce;
        }
    }



    public static class Request{

        @Data
        public static class SignupUSER {
            @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하여야 합니다.")
            private String username;
            private String password;
            private String realName;
            private String nickname;
            private String phone;
            public User toEntity(){
                return User.builder()
                        .username(username)
                        .password(password)
                        .realName(realName)
                        .nickname(nickname)
                        .phone(phone)
                        .build();
            }
        }

        @Data
        public static class SignupTRAINER {
            @Size(min = 4, max = 20, message = "아이디는 4자 이상 20자 이하여야 합니다.")
            private String username;
            //최소 8 자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상 :
            @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$",
                    message = "패스워드는 다음 규칙을 충족해야합니다.\n" +
                            "최소 8 자, 대문자 하나 이상, 소문자 하나, 숫자 하나 및 특수 문자 하나 이상")
            private String password;
            @Size(min = 2, max = 20, message = "이름은 2자 이상 20자 이하여야 합니다.")
            private String realName;
            @Min(value = 10000, message = "회당 가격을 제대로 입력해주세요.")
            private int price_per;
            @NotEmpty(message = "헬스장 코드를 입력해주세요.")
            private String gym_code;
            @NotEmpty(message = "자기소개를 입력해주세요.")
            private String introduce;
            @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "전화번호 형식이 올바르지 않습니다.")
            private String phone;

            public Trainer toEntity() {
                return Trainer.builder()
                        .username(username)
                        .password(password)
                        .realName(realName)
                        .pricePer(price_per)
                        .gymCode(gym_code)
                        .introduce(introduce)
                        .verified(false)
                        .phone(phone)
                        .build();
            }
        }
        @Data
        public static class Login {
            @NotEmpty(message = "아이디를 입력해주세요.")
            private String username;
            @NotEmpty(message = "비밀번호를 입력해주세요.")
            private String password;
            }
        }

}
