package com.common.reniors.dto.eval;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class UserEvalCreateRequest {

    private String score;
    private String memo;
    private Long evalQuestionId;
}
