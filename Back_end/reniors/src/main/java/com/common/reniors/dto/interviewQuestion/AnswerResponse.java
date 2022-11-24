package com.common.reniors.dto.interviewQuestion;

import com.common.reniors.domain.entity.interviewQuestion.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerResponse {

    @NotNull
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long questionId;

    @NotBlank
    private String answer;

    public static AnswerResponse response(Answer answer){
        return new AnswerResponse(answer.getId(), answer.getUser().getId(), answer.getQuestion().getId(), answer.getAnswer());
    }
}
