package com.common.reniors.domain.repository.resume;

import com.common.reniors.domain.entity.resume.CareerDetail;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerDetailRepository extends JpaRepository<CareerDetail, Long> {
    List<CareerDetail> findByUser(User user);
}
