package com.common.reniors.dto.category;

import com.common.reniors.domain.entity.category.JobParentCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobParentCategoryResponse{

    private Long id;

    private String name;

    public static JobParentCategoryResponse response(JobParentCategory jpc){
        return new JobParentCategoryResponse(jpc.getId(), jpc.getName());
    }
}