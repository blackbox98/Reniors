package com.common.reniors.dto.user;

import com.common.reniors.domain.entity.Type.Gender;
import com.common.reniors.domain.entity.Type.IsOpen;
import com.common.reniors.domain.entity.Type.LastEdu;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.resume.AwardResponse;
import com.common.reniors.dto.resume.CareerDetailResponse;
import com.common.reniors.dto.resume.LicenseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCompanyUseResponse {
    private Long id;

    private String name;

    private Date birth;

    private Gender gender;

    private String phone;

    private int totalCareer;

    private String address;

    private String extraAddress;

    private LastEdu lastEdu;

    private String baseURL;

    private String userProfile;

    private List<CareerDetailResponse> careerDetails;

    private List<AwardResponse> awards;

    private List<LicenseResponse> licenses;

    public static UserCompanyUseResponse response(
            User user,
            List<CareerDetailResponse> careerDetails,
            List<AwardResponse> awards,
            List<LicenseResponse> licenses
            ) {
        return new UserCompanyUseResponse(
                user.getId(),
                user.getName(),
                user.getBirth(),
                user.getGender(),
                user.getPhone(),
                user.getTotalCareer(),
                user.getAddress(),
                user.getExtraAddress(),
                user.getLastEdu(),
                user.getBaseURL(),
                user.getUserProfile(),
                careerDetails,
                awards,
                licenses
        );
    }
}
