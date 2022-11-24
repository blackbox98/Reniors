package com.common.reniors.domain.entity.notification;

import com.common.reniors.domain.entity.Apply;
import com.common.reniors.domain.entity.Type.IsRead;
import com.common.reniors.domain.entity.Type.JobOpeningProcess;
import com.common.reniors.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Notification extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JobOpeningProcess jobOpeningProcess;

    @NotNull
    @Enumerated(EnumType.STRING)
    private IsRead isRead;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apply_id")
    Apply apply;

    public static Notification create(JobOpeningProcess jobOpeningProcess, User user, Apply apply) {
        Notification notification = new Notification();
        notification.jobOpeningProcess = jobOpeningProcess;
        notification.isRead = IsRead.NOT_READ;
        notification.user = user;
        notification.apply = apply;
        return notification;
    }

    public void readNotification() {
        this.isRead = IsRead.READ;
    }
}
