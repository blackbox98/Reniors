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
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "award_id")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    private Date passedAt;

    @Column(length = 50)
    private String grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public static License create(String name, Date passedAt, String grade, User user) {
        License license = new License();
        license.name = name;
        license.passedAt = passedAt;
        license.grade = grade;
        license.user = user;
        return license;
    }

    public void update(String name, Date passedAt, String grade) {
        this.name = name;
        this.passedAt = passedAt;
        this.grade = grade;
    }
}
