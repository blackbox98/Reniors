package com.common.reniors.dto.category;

import com.common.reniors.domain.entity.category.Sido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SidoResponse{

    private Long id;

    private String name;

    private Long code;

    public static SidoResponse response(Sido sido){
        return new SidoResponse(sido.getId(), sido.getName(), sido.getCode());
    }
}