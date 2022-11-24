package com.common.reniors.dto.resume;

import com.common.reniors.domain.entity.resume.CareerDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerDetailResponse {
    private Long id;

    private String companyName;

    private Date startedAt;

    private Date finishedAt;

    private String jobContents;

    public static CareerDetailResponse response(CareerDetail careerDetail) {
        return new CareerDetailResponse(careerDetail.getId(),
                careerDetail.getCompanyName(),
                careerDetail.getStartedAt(),
                careerDetail.getFinishedAt(),
                careerDetail.getJobContents());
    }
}
