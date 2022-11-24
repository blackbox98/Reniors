package com.common.reniors.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyLoginRequest {

    @NotBlank
    private String companyAppId;

    @NotBlank
    private String companyAppPwd;
}
