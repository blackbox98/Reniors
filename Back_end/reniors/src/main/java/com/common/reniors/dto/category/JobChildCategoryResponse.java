package com.common.reniors.dto.category;

import com.common.reniors.domain.entity.category.JobChildCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobChildCategoryResponse {

    private Long id;

    private String name;

    public static JobChildCategoryResponse response(JobChildCategory jcc){
        return new JobChildCategoryResponse(jcc.getId(), jcc.getName());
    }
}