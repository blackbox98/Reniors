package com.common.reniors.domain.entity;

import com.common.reniors.domain.entity.category.Gugun;
import com.common.reniors.domain.entity.category.JobChildCategory;
import com.common.reniors.dto.jobOpening.HopeJobChildCreateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HopeJobChild {
    @Id @GeneratedValue
    @Column(name = "hope_job_child")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_condition_id")
    SearchCondition searchCondition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_child_category_id")
    JobChildCategory jobChildCategory;

    public HopeJobChild(SearchCondition searchCondition, JobChildCategory jobChildCategory){
        this.searchCondition = searchCondition;
        this.jobChildCategory = jobChildCategory;
    }

    public void update(SearchCondition searchCondition, JobChildCategory jobChildCategory){
        this.searchCondition = searchCondition;
        this.jobChildCategory = jobChildCategory;

    }
}
