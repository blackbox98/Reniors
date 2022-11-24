package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.SearchCondition;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchConditionRepository extends JpaRepository<SearchCondition, Long> {
    List<SearchCondition> findByUser(User user);
}
