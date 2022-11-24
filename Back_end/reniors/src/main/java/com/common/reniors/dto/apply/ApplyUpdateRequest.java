package com.common.reniors.dto.apply;

import com.common.reniors.domain.entity.Type.JobOpeningProcess;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class ApplyUpdateRequest {
    private JobOpeningProcess jobOpeningProcess;
    private Date interviewDate;
    private String sessionId;

}
