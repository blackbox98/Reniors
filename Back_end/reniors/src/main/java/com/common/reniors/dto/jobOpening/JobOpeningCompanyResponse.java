package com.common.reniors.dto.jobOpening;

import com.common.reniors.domain.entity.JobOpening;
import com.common.reniors.domain.entity.Type.IsFinish;
import com.common.reniors.domain.entity.Type.JobOpeningProcess;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningCompanyResponse {

    private Long id;
    private String title;
    private String jobChildCategoryName;
    private Date createdDate;
    private Date finishedDate;
    private int applies;
    private IsFinish isFinish;
    private JobOpeningProcess jobOpeningProcess;

    public static JobOpeningCompanyResponse response(JobOpening jobOpening){
        return new JobOpeningCompanyResponse(
                jobOpening.getId(),
                jobOpening.getTitle(),
                jobOpening.getJobChildCategory().getName(),
                jobOpening.getCreatedDate(),
                jobOpening.getFinishedDate(),
                jobOpening.getApplies().size(),
                jobOpening.getIsFinish(),
                jobOpening.getJobOpeningProcess()
        );
    }
}
