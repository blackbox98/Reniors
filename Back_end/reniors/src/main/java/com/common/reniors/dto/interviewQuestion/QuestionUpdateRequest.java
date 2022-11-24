package com.common.reniors.dto.interviewQuestion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUpdateRequest {

    @NotBlank
    private String question;

}
