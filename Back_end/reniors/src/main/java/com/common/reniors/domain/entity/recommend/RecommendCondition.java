package com.common.reniors.domain.entity.recommend;

import com.common.reniors.domain.entity.category.Gugun;
import com.common.reniors.domain.entity.category.JobParentCategory;
import com.common.reniors.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RecommendCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recommend_condition_id")
    private long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_parent_category_id")
    private JobParentCategory jobParentCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gugun_id")
    private Gugun gugun;

    private int workingDay;

    private int minSalary;

    public static RecommendCondition create(User user, JobParentCategory jobParentCategory, Gugun gugun, int workingDay, int minSalary) {
        RecommendCondition recommendCondition = new RecommendCondition();
        recommendCondition.user = user;
        recommendCondition.jobParentCategory = jobParentCategory;
        recommendCondition.gugun = gugun;
        recommendCondition.workingDay = workingDay;
        recommendCondition.minSalary = minSalary;
        return recommendCondition;
    }

    public void update(JobParentCategory jobParentCategory, Gugun gugun, int workingDay, int minSalary) {
        this.jobParentCategory = jobParentCategory;
        this.gugun = gugun;
        this.workingDay = workingDay;
        this.minSalary = minSalary;
    }
}
