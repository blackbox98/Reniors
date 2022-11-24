package com.common.reniors.domain.entity.recording;

import com.common.reniors.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Recording extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recording_id")
    private Long id;

    private String fileName;

    @NotNull
    private String recordURL;

    private String videoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Recording create(String fileName, String recordName, User user){
        Recording recording = new Recording();
        recording.fileName = fileName;
        recording.recordURL = recordName;
        recording.user = user;
        return recording;
    }

    public void videoSave(String videoId){
        this.videoId = videoId;
    }
}
