package com.common.reniors.domain.entity;

import com.common.reniors.domain.entity.Type.JobOpeningProcess;
import com.common.reniors.domain.entity.Type.LastEdu;
import com.common.reniors.domain.entity.Type.TypeEmployment;
import com.common.reniors.domain.entity.Type.IsFinish;
import com.common.reniors.domain.entity.category.Gugun;
import com.common.reniors.domain.entity.category.JobChildCategory;
import com.common.reniors.dto.jobOpening.JobOpeningCreateRequest;
import com.common.reniors.dto.jobOpening.JobOpeningUpdateRequest;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
public class JobOpening{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_opening_id")
    private Long id;

    @NotNull
//    @CreatedDate
    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private Date createdDate;

    @NotNull
//    @CreatedDate
    @DateTimeFormat(pattern = "YYYY-MM-DD")

    private Date finishedDate;

    @NotNull
    private int numberPeople;

    @NotNull
    private int minCareer;

    @NotNull
    @Column(length = 100)
    private String title;

    @NotNull
    @Column(length = 2000)
    private String contents;

    private int minSalary;

    @Column(length = 100)
    private String jobPosition;

    private int workingDay;

    @NotNull
    private int views;

    @Enumerated(EnumType.STRING)
    private IsFinish isFinish;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeEmployment typeEmployment;

    @Enumerated(EnumType.STRING)
    private LastEdu lastEdu;

    @Enumerated(EnumType.STRING)
    private JobOpeningProcess jobOpeningProcess;

    private String baseURL;

    private String jobOpeningImg;
    //공고 - 회사 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_child_category_id")
    private JobChildCategory jobChildCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gugun_id")
    private Gugun gugun;

    @OneToMany(mappedBy = "jobOpening", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Apply> applies = new ArrayList<>();

    @OneToMany(mappedBy = "jobOpening", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "jobOpening", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvalQuestion> evalQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "jobOpening", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookmark> bookmarks = new ArrayList<>();

    //공고 - 고용형태 연관관계
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "type_employment_id")
//    private TypeEmployment typeEmployment;

    //최종학력, 직무소분류, 구군 받아오기


    public JobOpening(JobOpeningCreateRequest jobOpeningCreateRequest, String baseURL, String jobOpeningImg, Company company,Gugun gugun, JobChildCategory jobChildCategory) {
        this.createdDate = jobOpeningCreateRequest.getCreatedDate();
        this.finishedDate = jobOpeningCreateRequest.getFinishedDate();
        this.numberPeople = jobOpeningCreateRequest.getNumberPeople();
        this.minCareer = jobOpeningCreateRequest.getMinCareer();
        this.title = jobOpeningCreateRequest.getTitle();
        this.contents = jobOpeningCreateRequest.getContents();
        this.minSalary = jobOpeningCreateRequest.getMinSalary();
        this.jobPosition = jobOpeningCreateRequest.getJobPosition();
        this.workingDay = jobOpeningCreateRequest.getWorkingDay();
        this.typeEmployment = jobOpeningCreateRequest.getTypeEmployment();
        this.lastEdu=jobOpeningCreateRequest.getLastEdu();
        this.isFinish = IsFinish.F;
        this.views = 0;
        this.jobOpeningProcess = JobOpeningProcess.서류심사중;
        this.baseURL = baseURL;
        this.jobOpeningImg = jobOpeningImg;
        this.company = company;
        this.gugun = gugun;
        this.jobChildCategory = jobChildCategory;
    }
    public void update(JobOpeningUpdateRequest jobOpeningUpdateRequest,String baseURL, String jobOpeningImg, Gugun gugun, JobChildCategory jobChildCategory){
        this.numberPeople = jobOpeningUpdateRequest.getNumberPeople();
        this.minCareer = jobOpeningUpdateRequest.getMinCareer();
        this.title = jobOpeningUpdateRequest.getTitle();
        this.contents = jobOpeningUpdateRequest.getContents();
        this.minSalary = jobOpeningUpdateRequest.getMinSalary();
        this.jobPosition = jobOpeningUpdateRequest.getJobPosition();
        this.workingDay = jobOpeningUpdateRequest.getWorkingDay();
        this.typeEmployment = jobOpeningUpdateRequest.getTypeEmployment();
        this.lastEdu=jobOpeningUpdateRequest.getLastEdu();
        this.baseURL =baseURL;
        this.jobOpeningImg = jobOpeningImg;
        this.gugun = gugun;
        this.jobChildCategory = jobChildCategory;
    }

    public void finish(){
        this.isFinish = IsFinish.T;
    }
    public void viewUp(){this.views+=1;}
    public void updateProgress(JobOpeningProcess jobOpeningProcess){this.jobOpeningProcess = jobOpeningProcess;}

}
