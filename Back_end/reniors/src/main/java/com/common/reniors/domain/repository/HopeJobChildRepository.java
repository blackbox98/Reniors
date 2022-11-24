package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.HopeJobChild;
import com.common.reniors.domain.entity.SearchCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopeJobChildRepository extends JpaRepository<HopeJobChild, Long> {
    List<HopeJobChild> findBySearchCondition(SearchCondition searchCondition);
}
