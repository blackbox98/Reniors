package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.Bookmark;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUser(User user);
    Optional<Bookmark> findById(Long bookmarkId);
}
