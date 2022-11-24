package com.common.reniors.common.exception;

public class NotFoundException extends RuntimeException {

    public static final String USER_NOT_FOUND = "존재하지 않는 회원입니다.";
    public static final String USER_LIST_NOT_FOUND = "회원이 존재하지 않습니다.";
    public static final String CATEGORY_NOT_FOUND = "존재하지 않는 카테고리입니다.";
    public static final String JOB_OPENING_NOT_FOUND = "존재하지 않는 공고입니다.";
    public static final String BOOKMARK_NOT_FOUND = "존재하지 않는 관심 공고입니다.";
    public static final String BOARD_NOT_FOUND = "존재하지 않는 게시글입니다.";
    public static final String COMMENT_NOT_FOUND = "존재하지 않는 댓글입니다.";
    public static final String AWARD_NOT_FOUND = "존재하지 않는 수상경력입니다.";
    public static final String CAREER_DETAIL_NOT_FOUND = "존재하지 않는 경력사항입니다.";
    public static final String LICENSE_NOT_FOUND = "존재하지 않는 자격증입니다.";
    public static final String RECOMMEND_CONDITION_NOT_FOUND = "존재하지 않는 추천 조건입니다.";
    public static final String QUESTION_NOT_FOUND = "존재하지 않는 질문입니다.";
    public static final String ANSWER_NOT_FOUND = "존재하지 않는 답변입니다.";
    public static final String RECORDING_NOT_FOUND = "존재하지 않는 녹화본입니다.";
    public static final String NOTIFICATION_NOT_FOUND = "존재하지 않는 알림입니다.";

    public NotFoundException(String message) {
        super(message);
    }
}