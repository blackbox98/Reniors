package com.common.reniors.domain.repository.board;

import com.common.reniors.domain.entity.board.Board;
import com.common.reniors.domain.entity.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard(Board board);
}
