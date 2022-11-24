package com.common.reniors.domain.entity;

import com.common.reniors.dto.eval.EvalQuestionCreateRequest;
import com.common.reniors.dto.eval.EvalQuestionUpdateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvalQuestion {
    @Id
    @GeneratedValue
    @Column(name = "eval_question_id")
    private Long id;

    @NotNull
    @Column(length = 500)
    private String contents;


    @OneToMany(mappedBy = "evalQuestion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEval> userEvals = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_opening_id")
    private JobOpening jobOpening;

    public EvalQuestion(EvalQuestionCreateRequest evalQuestionCreateRequest, JobOpening jobOpening) {
        this.contents = evalQuestionCreateRequest.getContents();
        this.jobOpening = jobOpening;
    }

    public void update(EvalQuestionUpdateRequest evalQuestionUpdateRequest) {
        this.contents = evalQuestionUpdateRequest.getContents();
    }
}
