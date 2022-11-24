package com.common.reniors.dto.eval;

import com.common.reniors.domain.entity.UserEval;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class UserEvalResponse {
    private String score;
    private String memo;
    private String contents;

    public static UserEvalResponse response(UserEval userEval){
        return new UserEvalResponse(
                userEval.getScore(),
                userEval.getMemo(),
                userEval.getEvalQuestion().getContents()
        );
    }

}
