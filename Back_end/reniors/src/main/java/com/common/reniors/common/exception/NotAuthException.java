package com.common.reniors.common.exception;

public class NotAuthException extends RuntimeException{

    public static final String COMPANY_NO_AUTH = "접근 권한이 없는 회사입니다.";

    public static final String USER_NO_AUTH = "권한이 없는 유저입니다.";

    public NotAuthException() {
        super();
    }

    public NotAuthException(String message) {
        super(message);
    }
}
