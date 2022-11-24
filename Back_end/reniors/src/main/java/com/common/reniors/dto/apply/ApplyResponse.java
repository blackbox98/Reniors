package com.common.reniors.dto.apply;

import com.common.reniors.domain.entity.Apply;
import com.common.reniors.domain.entity.Type.JobOpeningProcess;
import com.common.reniors.domain.entity.Type.Gender;
import lombok.*;

import java.util.Date;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class ApplyResponse {
    private Long id;
    private JobOpeningProcess jobOpeningProcess;
    private Date interviewDate;
    private Long jobOpeningId;
    private String jobOpeningName;
    private String jobChildCategoryName;
    private String sessionId;
    private Long userId;
    private String name;
    private Gender gender;
    private Date birth;
    private String phone;
    private String companyName;


    public static ApplyResponse response(Apply apply){
        return new ApplyResponse(
                apply.getId(),
                apply.getJobOpeningProcess(),
                apply.getInterviewDate(),
                apply.getJobOpening().getId(),
                apply.getJobOpening().getTitle(),
                apply.getJobOpening().getJobChildCategory().getName(),
                apply.getSessionId(),
                apply.getUser().getId(),
                apply.getUser().getName(),
                apply.getUser().getGender(),
                apply.getUser().getBirth(),
                apply.getUser().getPhone(),
                apply.getJobOpening().getCompany().getName()
        );
    }
}
