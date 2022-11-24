package com.common.reniors.dto.eval;

import com.common.reniors.domain.entity.EvalQuestion;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class EvalQuestionResponse {
    private Long id;
    private String contents;;

    public static EvalQuestionResponse response(EvalQuestion evalQuestion){
        return new EvalQuestionResponse(
                evalQuestion.getId(),
                evalQuestion.getContents()
        );
    }
}
