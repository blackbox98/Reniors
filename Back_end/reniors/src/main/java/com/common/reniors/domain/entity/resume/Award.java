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
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "award_id")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    private Date awardedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static Award create(String name, Date awardedAt, User user) {
        Award award = new Award();
        award.name = name;
        award.awardedAt = awardedAt;
        award.user = user;
        return award;
    }

    public void update(String name, Date awardedAt) {
        this.name = name;
        this.awardedAt = awardedAt;
    }
}
