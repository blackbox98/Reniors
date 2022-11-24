package com.common.reniors.dto.jobOpening;

import com.common.reniors.domain.entity.Type.LastEdu;
import com.common.reniors.domain.entity.Type.TypeEmployment;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class SearchConditionCreateRequest {
    private String name;
    private int minCareer;
    private int minSalary;
    private int workingDay;
    private TypeEmployment typeEmployment;
    private LastEdu lastEdu;
    private Long jobParentCategoryId;
}
