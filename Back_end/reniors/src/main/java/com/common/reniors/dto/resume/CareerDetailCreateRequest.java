package com.common.reniors.dto.resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CareerDetailCreateRequest {
    @NotBlank
    private String companyName;

    private Date startedAt;

    private Date finishedAt;

    private String jobContents;
}
