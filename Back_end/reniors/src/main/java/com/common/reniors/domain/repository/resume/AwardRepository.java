package com.common.reniors.domain.repository.resume;

import com.common.reniors.domain.entity.resume.Award;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardRepository extends JpaRepository<Award, Long> {
    List<Award> findByUser(User user);
}
