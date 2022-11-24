package com.common.reniors.dto.board;

import com.common.reniors.domain.entity.board.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponse {

    @NotNull
    Long boardId;

    @NotBlank
    String title;

    @NotNull
    Long userId;

    @NotBlank
    String userName;

    @NotNull LocalDateTime updatedAt;

    public static BoardResponse response(Board board){
        return new BoardResponse(board.getId(), board.getTitle(),board.getUser().getId(), board.getUser().getName(), board.getUpdatedAt());
    }
}
