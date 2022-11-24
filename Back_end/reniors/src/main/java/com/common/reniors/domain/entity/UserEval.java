package com.common.reniors.domain.entity;

import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.eval.UserEvalCreateRequest;
import com.common.reniors.dto.eval.UserEvalUpdateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEval {
    @Id @GeneratedValue
    @Column(name = "user_eval_id")
    private Long id;

    @Column(length = 100)
    private String score;

    @Column(length = 1000)
    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eval_question_id")
    private EvalQuestion evalQuestion;

    public UserEval(UserEvalCreateRequest userEvalCreateRequest,User user, EvalQuestion evalQuestion){
        this.score = userEvalCreateRequest.getScore();
        this.memo = userEvalCreateRequest.getMemo();
        this.user = user;
        this.evalQuestion = evalQuestion;
    }

    public void update(UserEvalUpdateRequest userEvalUpdateRequest){
        this.score = userEvalUpdateRequest.getScore();
        this.memo = userEvalUpdateRequest.getMemo();
    }


}
