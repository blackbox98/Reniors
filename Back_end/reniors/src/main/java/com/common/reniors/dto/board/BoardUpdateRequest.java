package com.common.reniors.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateRequest {
    @NotBlank
    String title;

    @NotBlank
    String contents;
}
