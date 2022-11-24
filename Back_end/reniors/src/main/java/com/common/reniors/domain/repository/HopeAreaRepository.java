package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.HopeArea;
import com.common.reniors.domain.entity.SearchCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HopeAreaRepository extends JpaRepository<HopeArea, Long> {
    List<HopeArea> findBySearchCondition(SearchCondition searchCondition);
}
