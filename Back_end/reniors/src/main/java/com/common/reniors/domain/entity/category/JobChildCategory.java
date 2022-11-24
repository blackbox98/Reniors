package com.common.reniors.domain.entity.category;

import com.common.reniors.domain.entity.HopeJobChild;
import com.common.reniors.domain.entity.SearchCondition;
import com.common.reniors.domain.entity.recommend.RecommendCondition;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class JobChildCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_child_category_id")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    @NotNull
    private Long code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_parent_category_id")
    private JobParentCategory parent;

    @OneToMany(mappedBy = "jobChildCategory")
    private List<HopeJobChild> hopeJobChilds = new ArrayList<>();

    public static JobChildCategory create(String name, Long code,JobParentCategory parent){
        JobChildCategory jcc = new JobChildCategory();
        jcc.name = name;
        jcc.code = code;
        jcc.parent = parent;
        return jcc;
    }

    public void update(String name, Long code, JobParentCategory parent){
        this.name = name;
        this.code = code;
        this.parent = parent;
    }
}