package com.common.reniors.domain.entity.resume;

import com.common.reniors.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CareerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "career_detail_id")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String companyName;

    private Date startedAt;

    private Date finishedAt;

    @Column(length = 500)
    private String jobContents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static CareerDetail create(String companyName, Date startedAt, Date finishedAt, String jobContents, User user) {
        CareerDetail careerDetail = new CareerDetail();
        careerDetail.companyName = companyName;
        careerDetail.startedAt = startedAt;
        careerDetail.finishedAt = finishedAt;
        careerDetail.jobContents = jobContents;
        careerDetail.user = user;
        return careerDetail;
    }

    public void update(String companyName, Date startedAt, Date finishedAt, String jobContents) {
        this.companyName = companyName;
        this.startedAt = startedAt;
        this.finishedAt = finishedAt;
        this.jobContents = jobContents;
    }
}
