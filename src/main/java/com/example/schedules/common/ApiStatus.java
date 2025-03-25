package com.example.schedules.common;

public enum ApiStatus {

    SUCCESS(200, "요청이 성공적으로 처리되었습니다."),
    CREATED(201, "리소스가 생성되었습니다."),


    BAD_REQUEST(400, "잘못된 요청입니다."),
    NOT_FOUND(404, "리소스를 찾을 수 없습니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 일치하지 않습니다."),
    AUTHOR_NOT_FOUND(404, "작성자를 찾을 수 없습니다."),


    INTERNAL_SERVER_ERROR(500, "서버 내부 오류가 발생했습니다.");

    private final int code;
    private final String message;

    ApiStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
