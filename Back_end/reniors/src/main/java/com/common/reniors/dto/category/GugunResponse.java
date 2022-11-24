package com.common.reniors.dto.category;

import com.common.reniors.domain.entity.category.Gugun;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GugunResponse {

    private Long id;

    private String name;

    private Long code;

    public static GugunResponse response(Gugun gugun){
        return new GugunResponse(gugun.getId(), gugun.getName(), gugun.getCode());
    }
}