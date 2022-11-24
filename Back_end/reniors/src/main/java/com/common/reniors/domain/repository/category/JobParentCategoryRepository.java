package com.common.reniors.domain.repository.category;

import com.common.reniors.domain.entity.category.JobParentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobParentCategoryRepository extends JpaRepository<JobParentCategory, Long> {

    Optional<JobParentCategory> findByName(String name);
    Optional<JobParentCategory> findByCode(Long code);
}
