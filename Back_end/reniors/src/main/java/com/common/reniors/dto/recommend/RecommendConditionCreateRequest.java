package com.common.reniors.dto.recommend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendConditionCreateRequest {
    private Long jobParentCategoryId;

    private Long gugunId;

    private int workingDay;

    private int minSalary;
}
