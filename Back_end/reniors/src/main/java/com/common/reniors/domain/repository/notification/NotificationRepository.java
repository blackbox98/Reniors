package com.common.reniors.domain.repository.notification;

import com.common.reniors.domain.entity.notification.Notification;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUser(User user);
}
