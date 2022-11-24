package com.common.reniors.dto.jobOpening;

import com.common.reniors.domain.entity.JobOpening;
import com.common.reniors.domain.entity.Type.IsFinish;
import com.common.reniors.domain.entity.Type.TypeEmployment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningResponse {

    private Long id;
    private Date createdDate;
    private Date finishedDate;
    private String title;
    private IsFinish isFinish;
    private String companyName;
    private TypeEmployment typeEmployment;
    private int minSalary;
    private String sido;
    private String gugun;
    private int workingDay;
    private String baseURL;
    private String companyProfile;

    public static JobOpeningResponse response(JobOpening jobOpening){
        return new JobOpeningResponse(
                jobOpening.getId(),
                jobOpening.getCreatedDate(),
                jobOpening.getFinishedDate(),
                jobOpening.getTitle(),
                jobOpening.getIsFinish(),
                jobOpening.getCompany().getName(),
                jobOpening.getTypeEmployment(),
                jobOpening.getMinSalary(),
                jobOpening.getGugun().getSido().getName(),
                jobOpening.getGugun().getName(),
                jobOpening.getWorkingDay(),
                jobOpening.getCompany().getBaseURL(),
                jobOpening.getCompany().getCompanyProfile()
        );
    }
}
