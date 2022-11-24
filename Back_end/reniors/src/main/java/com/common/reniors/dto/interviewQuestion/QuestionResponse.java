package com.common.reniors.dto.interviewQuestion;

import com.common.reniors.domain.entity.interviewQuestion.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponse {

    @NotNull
    private Long id;

    @NotBlank
    private String question;

    public static QuestionResponse response(Question question){
        return new QuestionResponse(question.getId(), question.getQuestion());
    }
}
