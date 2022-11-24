package com.common.reniors.dto.resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardUpdateRequest {
    @NotBlank
    private String name;

    private Date awardedAt;
}
