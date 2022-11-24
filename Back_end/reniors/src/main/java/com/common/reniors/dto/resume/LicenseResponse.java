package com.common.reniors.dto.resume;

import com.common.reniors.domain.entity.resume.License;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LicenseResponse {
    private Long id;

    private String name;

    private Date passedAt;

    private String grade;

    public static LicenseResponse response(License license) {
        return new LicenseResponse(license.getId(),
                license.getName(),
                license.getPassedAt(),
                license.getGrade());
    }
}
