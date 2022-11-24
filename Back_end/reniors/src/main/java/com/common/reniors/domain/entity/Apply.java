package com.common.reniors.domain.entity;

import com.common.reniors.domain.entity.Type.JobOpeningProcess;
import com.common.reniors.domain.entity.notification.Notification;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.apply.ApplyUpdateRequest;
import com.common.reniors.dto.apply.SessionUpdateRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apply {
    @Id
    @GeneratedValue
    @Column(name = "apply_id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JobOpeningProcess jobOpeningProcess;

    private String sessionId;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date interviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_opening_id")
    JobOpening jobOpening;

    @OneToMany(mappedBy = "apply", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications = new ArrayList<>();

    public Apply(User user, JobOpening jobOpening) {
        this.jobOpeningProcess = JobOpeningProcess.서류심사중;
        this.user = user;
        this.jobOpening = jobOpening;
    }

    public void update(ApplyUpdateRequest applyUpdateRequest, User user, JobOpening jobOpening) {
        this.jobOpeningProcess = applyUpdateRequest.getJobOpeningProcess();
        this.interviewDate = applyUpdateRequest.getInterviewDate();
        this.sessionId = applyUpdateRequest.getSessionId();
        this.user = user;
        this.jobOpening = jobOpening;
    }

    public void sessionUpdate(SessionUpdateRequest sessionUpdateRequest){
        this.sessionId = sessionUpdateRequest.getSessionId();
    }

    public void finishInterview(){
        this.sessionId = null;
        this.interviewDate = null;
        this.jobOpeningProcess = JobOpeningProcess.면접심사중;
    }
}
