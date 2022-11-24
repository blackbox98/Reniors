package com.common.reniors.dto.jobOpening;

import lombok.*;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
@AllArgsConstructor
public class HopeJobChildCreateRequest {
    private Long id;

    private Long jobChildCategoryId;

}
