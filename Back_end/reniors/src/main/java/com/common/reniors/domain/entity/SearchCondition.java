package com.common.reniors.domain.entity;

import com.common.reniors.domain.entity.Type.TypeEmployment;
import com.common.reniors.domain.entity.Type.LastEdu;
import com.common.reniors.domain.entity.category.JobChildCategory;
import com.common.reniors.domain.entity.category.JobParentCategory;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.jobOpening.SearchConditionCreateRequest;
import com.common.reniors.dto.jobOpening.SearchConditionUpdateRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchCondition {
    @Id
    @GeneratedValue
    @Column(name = "search_condition_id")
    private Long id;

    @NotNull
    @Column(length = 50)
    private String name;

    private int minCareer;

    private int minSalary;

    private int workingDay;

    @Enumerated(EnumType.STRING)
    private TypeEmployment typeEmployment;

    @Enumerated(EnumType.STRING)
    private LastEdu lastEdu;

    private Long jobParentCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "searchCondition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HopeArea> hopeAreas = new ArrayList<>();

    @OneToMany(mappedBy = "searchCondition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HopeJobChild> hopeJobChilds = new ArrayList<>();

    public SearchCondition(SearchConditionCreateRequest searchConditionCreateRequest, User user) {
        this.name = searchConditionCreateRequest.getName();
        this.minCareer = searchConditionCreateRequest.getMinCareer();
        this.minSalary = searchConditionCreateRequest.getMinSalary();
        this.workingDay = searchConditionCreateRequest.getWorkingDay();
        this.typeEmployment = searchConditionCreateRequest.getTypeEmployment();
        this.lastEdu = searchConditionCreateRequest.getLastEdu();
        this.jobParentCategoryId = searchConditionCreateRequest.getJobParentCategoryId();
        this.user = user;
    }

    public void update(SearchConditionUpdateRequest searchConditionUpdateRequest, User user) {
        this.name = searchConditionUpdateRequest.getName();
        this.minCareer = searchConditionUpdateRequest.getMinCareer();
        this.minSalary = searchConditionUpdateRequest.getMinSalary();
        this.workingDay = searchConditionUpdateRequest.getWorkingDay();
        this.typeEmployment = searchConditionUpdateRequest.getTypeEmployment();
        this.lastEdu = searchConditionUpdateRequest.getLastEdu();
        this.jobParentCategoryId = searchConditionUpdateRequest.getJobParentCategoryId();
        this.user = user;
    }
}
