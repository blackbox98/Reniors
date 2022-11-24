package com.common.reniors.domain.entity;

import com.common.reniors.domain.entity.Type.IsActive;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.room.RoomCreateRequest;
import com.common.reniors.dto.room.RoomUpdateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {
    @Id @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private IsActive isActive;
    @NotNull
    @Column(length = 200)
    private String sessionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_opening_id")
    private JobOpening jobOpening;

    public Room(RoomCreateRequest roomCreateRequest,User user,JobOpening jobOpening){
        this.title = roomCreateRequest.getTitle();
        this.isActive = roomCreateRequest.getIsActive();
        this.sessionId = roomCreateRequest.getSessionId();
        this.user = user;
        this.jobOpening = jobOpening;
    }

    public void update(RoomUpdateRequest roomUpdateRequest){
        this.title = roomUpdateRequest.getTitle();
        this.isActive = roomUpdateRequest.getIsActive();
    }
}
