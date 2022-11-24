package com.common.reniors.domain.repository.recommend;

import com.common.reniors.domain.entity.recommend.RecommendCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendConditionRepository extends JpaRepository<RecommendCondition, Long> {
    Optional<RecommendCondition> findByUserId(Long userId);
}
