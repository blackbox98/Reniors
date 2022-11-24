package com.common.reniors.domain.repository.user;

import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserAppId(String userAppId);
    Optional<User> findByKakaoId(String kakaoId);
    Optional<User> findByNameAndPhone(String name, String phone);
    Optional<User> findByNameAndUserAppId(String name, String phone);
}
