package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.UserEval;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEvalRepository extends JpaRepository<UserEval, Long> {
    List<UserEval> findByUser(User user);
}
