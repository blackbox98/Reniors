package com.common.reniors.dto.jobOpening;

import com.common.reniors.domain.entity.HopeArea;
import com.common.reniors.domain.entity.HopeJobChild;
import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class HopeJobChildResponse {
    private Long id;
    private String jobChildCategory;

    public static HopeJobChildResponse response(HopeJobChild hopeJobChild){
        return new HopeJobChildResponse(
                hopeJobChild.getId(),
                hopeJobChild.getJobChildCategory().getName()
        );
    }
}
