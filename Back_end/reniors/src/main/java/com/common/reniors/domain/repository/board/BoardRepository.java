package com.common.reniors.domain.repository.board;

import com.common.reniors.domain.entity.board.Board;
import com.common.reniors.domain.entity.category.JobParentCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByCategory(JobParentCategory jpc, Pageable pageable);
}
