package com.common.reniors.service.board;

import com.common.reniors.common.exception.NotAuthException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.board.Board;
import com.common.reniors.domain.entity.board.Comment;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.board.BoardRepository;
import com.common.reniors.domain.repository.board.CommentRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.board.CommentCreateRequest;
import com.common.reniors.dto.board.CommentResponse;
import com.common.reniors.dto.board.CommentUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotAuthException.USER_NO_AUTH;
import static com.common.reniors.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    @Transactional
    public Long create(Long boardId, CommentCreateRequest request, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        Comment comment = Comment.create(request.getContents(), board, user);
        return commentRepository.save(comment).getId();
    }

    @Transactional
    public void update(Long commentId, CommentUpdateRequest request, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new NotFoundException(COMMENT_NOT_FOUND));
        if(comment.getUser().getId() == user.getId()) {
            comment.update(request.getContents());
        }else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

    @Transactional
    public void delete(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(()->new NotFoundException(COMMENT_NOT_FOUND));
        if(comment.getUser().getId() == user.getId()) {
            commentRepository.delete(comment);
        }else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

    @Transactional
    public List<CommentResponse> getCommentList(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        List<CommentResponse> comments = commentRepository.findByBoard(board).stream()
                .map(CommentResponse::response)
                .collect(Collectors.toList());
        return comments;
    }
}
