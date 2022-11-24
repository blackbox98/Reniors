package com.common.reniors.dto.resume;

import com.common.reniors.domain.entity.resume.Award;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AwardResponse {
    private Long id;

    private String name;

    private Date awardedAt;

    public static AwardResponse response(Award award) {
        return new AwardResponse(award.getId(),
                award.getName(),
                award.getAwardedAt());
    }
}
