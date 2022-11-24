package com.common.reniors.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSearchRequest {
    @NotNull
    Long categoryId;

    String name;

    String title;

    Long boardId;
}
