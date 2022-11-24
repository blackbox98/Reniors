package com.common.reniors.domain.repository.resume;

import com.common.reniors.domain.entity.resume.License;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicenseRepository extends JpaRepository<License, Long> {
    List<License> findByUser(User user);
}
