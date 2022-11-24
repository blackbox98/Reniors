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
public class BoardDetailResponse {

    @NotNull
    Long boardId;

    @NotBlank
    String title;

    @NotNull
    Long userId;

    @NotBlank
    String contents;

    @NotBlank
    String userName;

    @NotNull
    String baseURL;

    @NotNull
    String userProfile;

    @NotNull LocalDateTime updatedAt;

    public static BoardDetailResponse response(Board board){
        return new BoardDetailResponse(board.getId(), board.getTitle(),board.getUser().getId(), board.getContents(), board.getUser().getName(), board.getUser().getBaseURL(), board.getUser().getUserProfile(), board.getUpdatedAt());
    }
}
