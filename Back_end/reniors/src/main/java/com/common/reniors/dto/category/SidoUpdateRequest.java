package com.common.reniors.dto.category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SidoUpdateRequest {
    @NotBlank(message = "시도 이름은 필수입니다.")
    private String name;

    @NotNull(message = "시도 분류코드는 필수입니다.")
    private Long code;
}