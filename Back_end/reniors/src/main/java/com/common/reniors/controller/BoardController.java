package com.common.reniors.controller;

import com.common.reniors.common.config.web.LoginUser;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.board.*;
import com.common.reniors.service.board.BoardService;
import com.common.reniors.service.board.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Api(tags={"게시판 API"})
public class BoardController {
    private final BoardService boardService;
    private final CommentService commentService;

    @PostMapping
    @ApiOperation(value = "게시글 생성", notes = "게시글을 생성한다.")
    public ResponseEntity<?> createBoard(
            @ApiIgnore @LoginUser User user,
            @Valid @RequestBody BoardCreateRequest request
            ){
        Long boardId = boardService.create(request, user);
        Map<String, Long> response = new HashMap<>();
        response.put("boardId", boardId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/list")
    @ApiOperation(value = "게시글 조회", notes = "게시글을 조회한다.")
    public ResponseEntity<?> getBoardList(
            @RequestBody BoardSearchRequest request,
            Pageable pageable
            ){
        Map<String, Object> response = new HashMap<>();
        return ResponseEntity.ok(boardService.getBoardList(request, pageable));
    }

    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시글 상세조회", notes = "게시글을 상세조회한다.")
    public ResponseEntity<?> getBoardInfo(
            @PathVariable Long boardId

    ){
        return ResponseEntity.ok(boardService.getBoardInfo(boardId));
    }

    @PutMapping("/{boardId}")
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    public ResponseEntity<?> updateBoard(
            @ApiIgnore @LoginUser User user,
            @PathVariable Long boardId,
            @Valid @RequestBody BoardUpdateRequest request
            ){
        boardService.update(boardId, request, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    public ResponseEntity<?> deleteBoard(
            @ApiIgnore @LoginUser User user,
            @PathVariable Long boardId
    ){
        boardService.delete(boardId, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/{boardId}/comments")
    @ApiOperation(value = "댓글 생성", notes = "해당 게시글에 댓글을 생성한다.")
    public ResponseEntity<?> createComment(
            @ApiIgnore @LoginUser User user,
            @PathVariable Long boardId,
            @Valid @RequestBody CommentCreateRequest request
    ){
        Long commentId = commentService.create(boardId, request, user);
        Map<String, Long> response = new HashMap<>();
        response.put("commentId", commentId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{boardId}/comments")
    @ApiOperation(value = "댓글 조회", notes = "해당 게시글의 댓글을 조회한다.")
    public ResponseEntity<?> getCommentList(
            @PathVariable("boardId") Long boardId
            ){
        return ResponseEntity.ok(commentService.getCommentList(boardId));
    }

    @PutMapping("/{boardId}/comments/{commentId}")
    @ApiOperation(value = "댓글 수정", notes = "해당 게시글의 댓글을 수정한다.")
    public ResponseEntity<?> updateComment(
            @ApiIgnore @LoginUser User user,
            @PathVariable("boardId") Long boardId,
            @PathVariable("commentId") Long commentId,
            @Valid @RequestBody CommentUpdateRequest request
    ){
        commentService.update(commentId, request, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{boardId}/comments/{commentId}")
    @ApiOperation(value = "댓글 삭제", notes = "해당 게시글의 댓글을 삭제한다.")
    public ResponseEntity<?> deleteComment(
            @ApiIgnore @LoginUser User user,
            @PathVariable("boardId") Long boardId,
            @PathVariable("commentId") Long commentId
    ){
        commentService.delete(commentId, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
