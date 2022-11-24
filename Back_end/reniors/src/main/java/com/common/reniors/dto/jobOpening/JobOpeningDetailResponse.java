package com.common.reniors.dto.jobOpening;

import com.common.reniors.domain.entity.Type.IsFinish;
import com.common.reniors.domain.entity.Type.TypeEmployment;
import com.common.reniors.domain.entity.Type.LastEdu;
import com.common.reniors.domain.entity.JobOpening;
import com.common.reniors.dto.company.CompanyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOpeningDetailResponse {
    private Long id;
    private Date createdDate;
    private Date finishedDate;
    private int numberPeople;
    private int minCareer;
    private String title;
    private String contents;
    private int minSalary;
    private String jobPosition;
    private int workingDay;
    private TypeEmployment typeEmployment;
    private LastEdu lastEdu;
    private String baseURL;
    private String jobOpeningImg;
    private Long sidoId;
    private Long gugunId;
    private Long jobParentCategory;
    private Long jobChildCategoryId;
    private IsFinish isFinish;
    private CompanyResponse companyResponse;
    private List<JobOpeningResponse> companyJobOpening;

    public static JobOpeningDetailResponse response(JobOpening jobOpening){
        return new JobOpeningDetailResponse(
                jobOpening.getId(),
                jobOpening.getCreatedDate(),
                jobOpening.getFinishedDate(),
                jobOpening.getNumberPeople(),
                jobOpening.getMinCareer(),
                jobOpening.getTitle(),
                jobOpening.getContents(),
                jobOpening.getMinSalary(),
                jobOpening.getJobPosition(),
                jobOpening.getWorkingDay(),
                jobOpening.getTypeEmployment(),
                jobOpening.getLastEdu(),
                jobOpening.getBaseURL(),
                jobOpening.getJobOpeningImg(),
                jobOpening.getGugun().getSido().getId(),
                jobOpening.getGugun().getId(),
                jobOpening.getJobChildCategory().getParent().getId(),
                jobOpening.getJobChildCategory().getId(),
                jobOpening.getIsFinish(),
                CompanyResponse.response(jobOpening.getCompany()),
                jobOpening.getCompany().getJobOpenings().stream().map(j->JobOpeningResponse.response(j)).collect(Collectors.toList())
        );
    }
}
