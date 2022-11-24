package com.common.reniors.service.board;

import com.common.reniors.common.exception.NotAuthException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.board.Board;
import com.common.reniors.domain.entity.board.QBoard;
import com.common.reniors.domain.entity.category.JobParentCategory;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.board.BoardRepository;
import com.common.reniors.domain.repository.category.JobParentCategoryRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.board.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotAuthException.USER_NO_AUTH;
import static com.common.reniors.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService{

    private final BoardRepository boardRepository;
    private final JobParentCategoryRepository jobParentCategoryRepository;
    private final UserRepository userRepository;

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Long create(BoardCreateRequest request,User user) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        Board board = Board.create(request.getTitle(), request.getContents(), user, jpc);
        return boardRepository.save(board).getId();
    }

    @Transactional
    public void update(Long boardId, BoardUpdateRequest request, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        if(board.getUser().getId() == user.getId()) {
            board.update(request.getTitle(), request.getContents());
        }
        else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

    @Transactional
    public void delete(Long boardId, User user) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        if(board.getUser().getId() == user.getId()) {
            boardRepository.delete(board);
        }else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

    @Transactional
    public Page<BoardResponse> getBoardList(BoardSearchRequest boardSearchRequest, Pageable pageable) {

        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        JobParentCategory jpc = jobParentCategoryRepository.findById(boardSearchRequest.getCategoryId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));

        Page<BoardResponse> boardList = boardRepository.findByCategory(jpc, pageable)
                .map(BoardResponse::response);

        if(boardList.getContent().size() != 0){
            return boardList;
        }
        return null;
    }

    @Transactional
    public BoardDetailResponse getBoardInfo(Long boardId){
        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->new NotFoundException(BOARD_NOT_FOUND));
        board.viewsUp();
        return BoardDetailResponse.response(board);
    }
}
