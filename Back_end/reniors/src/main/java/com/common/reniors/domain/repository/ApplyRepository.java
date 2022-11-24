package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.Apply;
import com.common.reniors.domain.entity.JobOpening;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply,Long> {
    List<Apply> findByJobOpening(JobOpening jobOpening);
    List<Apply> findByUser(User user);
}
