package com.common.reniors.service.notification;

import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.Apply;
import com.common.reniors.domain.entity.notification.Notification;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.ApplyRepository;
import com.common.reniors.domain.repository.notification.NotificationRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.notification.NotificationCreateRequest;
import com.common.reniors.dto.notification.NotificationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotFoundException.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationService {
    private final UserRepository userRepository;
    private final ApplyRepository applyRepository;
    private final NotificationRepository notificationRepository;

    @Transactional
    public Long create(NotificationCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        Apply apply = applyRepository.findById(request.getApplyId())
                .orElseThrow(()->new NotFoundException(JOB_OPENING_NOT_FOUND));
        Notification notification = Notification.create(request.getJobOpeningProcess(), user, apply);
        return notificationRepository.save(notification).getId();
    }

    @Transactional
    public List<NotificationResponse> readList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        List<NotificationResponse> notifications = notificationRepository.findByUser(user).stream()
                .map(NotificationResponse::response)
                .collect(Collectors.toList());
        return notifications;
    }

    @Transactional
    public void read(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(()->new NotFoundException(NOTIFICATION_NOT_FOUND));
        notification.readNotification();
    }

    @Transactional
    public void delete(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(()->new NotFoundException(NOTIFICATION_NOT_FOUND));
        notificationRepository.delete(notification);
    }
}
