package com.bulkup.health.config.exception;

import org.springframework.http.HttpStatus;
public enum ErrorCode {
    // Authentication
    AUTHENTICATION_FAILED(HttpStatus.UNAUTHORIZED.value(), "A001", "자격증명에 실패했습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN.value(), "A002", "접근이 거부되었습니다."),
    // Common
    METHOD_NOT_ALLOWED(405, "C002", " method not allowed"),
    HANDLE_ACCESS_DENIED(403, "C003", "잘못된 접근입니다."),
    CANNOT_LOAD_USERNAME(500, "C004", "존재하지 않는 username입니다"),
    DOESNT_EXIST_TOKEN(501, "C005", "존재하지 않는 토큰"),
    ONLY_ACCESS_USER(501, "C006", "유저만 사용 할 수 있는 기능입니다."),

    ONLY_ACCESS_TRAINER(501, "C007", "트레이너만 사용 할 수 있는 기능입니다."),
    CANT_ACCESS_ADMIN(501, "C008", "어드민은 사용 할 수 없는 기능입니다."),
    ONLY_ACCESS_USER_ADMIN(501, "C008", "어드민은 사용 할 수 없는 기능입니다."),
    ONLY_ACCESS_CREW(501, "C009", "크루가 아닌 파티엔 참가할 수 없습니다."),
    ALREADY_JOINED(501, "C010", "이미 참가중인 파티입니다."),
    TIME_VALUE_ERROR(501, "C011", "시간값이 잘못되었습니다."),

    // Member
    EMAIL_DUPLICATION(409, "M001", "존재하는 이메일"),
    NICKNAME_DUPLICATION(409, "M002", "존재하는 닉네임"),
    USERNAME_DUPLICATION(409, "M003", "존재하는 아이디"),
    PHONE_DUPLICATION(409, "M004", "존재하는 휴대폰 번호"),

    MEMBER_NOT_FOUND(403, "M005", "존재하지 않은 회원정보"),
    PARTY_TRAINER_ALREADY_EXIST(403, "M006", "이미 배정된 트레이너가 존재하는 파티입니다."),
    TRAINER_NOT_ACTIVATED(501, "M007", "가입 승인 되지 않은 트레이너입니다. 승인까지 기다려주세요."),
    USER_BAN(501, "M008", "로그인 할수 없는 계정입니다. 고객센터에 문의해주세요."),

    // unchcked
    UNCHECKED_ERROR(500, "U001", "확인 할 수 없는 에러"),
    HANDLE_INVALID_PARAMETER(400, "H001", "잘못된 파라미터");
    private final String errorCodeStr;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String errorCodeStr, final String message) {
        this.status = status;
        this.message = message;
        this.errorCodeStr = errorCodeStr;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCodeStr;
    }
}
