package com.common.reniors.service.jobOpening;

import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.*;
import com.common.reniors.domain.entity.Type.IsFinish;
import com.common.reniors.domain.entity.category.Gugun;
import com.common.reniors.domain.entity.category.JobChildCategory;
import com.common.reniors.domain.entity.recommend.RecommendCondition;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.*;
import com.common.reniors.domain.repository.category.GugunRepository;
import com.common.reniors.domain.repository.category.JobChildCategoryRepository;
import com.common.reniors.domain.repository.category.JobParentCategoryRepository;
import com.common.reniors.domain.repository.recommend.RecommendConditionRepository;
import com.common.reniors.dto.apply.ApplyResponse;
import com.common.reniors.dto.category.GugunResponse;
import com.common.reniors.dto.category.JobChildCategoryResponse;
import com.common.reniors.dto.jobOpening.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobOpeningService {
    private final JobOpeningRepository jobOpeningRepository;
    private final GugunRepository gugunRepository;
    private final JobChildCategoryRepository jobChildCategoryRepository;
    private final JobParentCategoryRepository jobParentCategoryRepository;
    private final ApplyRepository applyRepository;
    private final HopeAreaRepository hopeAreaRepository;
    private final HopeJobChildRepository hopeJobChildRepository;
    private final SearchConditionRepository searchConditionRepository;
    private final RecommendConditionRepository recommendConditionRepository;

    @PersistenceContext
    EntityManager em;

    //공고 조건 생성(지역, 직무 제외)
    @Transactional
    public Long postSearchCondition(User user, SearchConditionCreateRequest searchConditionCreateRequest) {
        SearchCondition searchCondition = new SearchCondition(searchConditionCreateRequest, user);

        Long id = searchConditionRepository.save(searchCondition).getId();

        return id;
    }

    //공고 조건 희망지역 설정
    @Transactional
    public Long postHopeArea(Long searchConditionId, HopeAreaCreateRequest hopeAreaCreateRequest) {
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        Gugun gugun = gugunRepository.findById(hopeAreaCreateRequest.getGugunId()).orElseThrow(() -> new NotFoundException("not found gugun"));

        HopeArea hopeArea = new HopeArea(hopeAreaCreateRequest, searchCondition, gugun);
        Long id = hopeAreaRepository.save(hopeArea).getId();

        return id;
    }

    //공고 조건 희망지역 수정
    @Transactional
    public void updateHopeArea(Long searchConditionId, HopeAreaUpdateRequest hopeAreaUpdateRequest, Long hopeAreaId) {
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        Gugun gugun = gugunRepository.findById(hopeAreaUpdateRequest.getGugunId()).orElseThrow(() -> new NotFoundException("not found gugun"));
        HopeArea hopeArea = hopeAreaRepository.findById(hopeAreaId).orElseThrow(() -> new NotFoundException("not found area"));

        hopeArea.update(searchCondition, gugun);
    }


    //공고 조건 희망지역 삭제
    @Transactional
    public void deleteHopeArea(Long hopeAreaId) {

        hopeAreaRepository.deleteById(hopeAreaId);
    }

    //공고 조건 희망직무 설정
    @Transactional
    public Long postHopeJobChild(Long searchConditionId, HopeJobChildCreateRequest hopeJobChildCreateRequest) {
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(hopeJobChildCreateRequest.getJobChildCategoryId()).orElseThrow(() -> new NotFoundException("not found jobChildCategory"));

        HopeJobChild hopeJobChild = new HopeJobChild(searchCondition, jobChildCategory);
        Long id = hopeJobChildRepository.save(hopeJobChild).getId();

        return id;
    }

    //공고 조건 희망직무 수정
    @Transactional
    public void updateHopeJobChild(Long searchConditionId, HopeJobChildUpdateRequest hopeJobChildUpdateRequest, Long hopeJobChildId) {
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(hopeJobChildUpdateRequest.getJobChildCategoryId()).orElseThrow(() -> new NotFoundException("not found jobChildCategory"));
        HopeJobChild hopeJobChild = hopeJobChildRepository.findById(hopeJobChildId).orElseThrow(() -> new NotFoundException("not found hopejob"));

        hopeJobChild.update(searchCondition, jobChildCategory);
    }

    //공고 조건 희망직무 삭제
    @Transactional
    public void deleteHopeJobChild(Long hopeJobChildId) {
        hopeJobChildRepository.deleteById(hopeJobChildId);
    }

    //공고 조건 조회
    @Transactional
    public List<SearchConditionResponse> getSearchConditionList(User user) {
        List<SearchCondition> searchConditionList = searchConditionRepository.findByUser(user);
        List<SearchConditionResponse> searchConditionResponseList = new ArrayList<>();

        for (SearchCondition searchCondition : searchConditionList) {
            if (searchCondition.getJobParentCategoryId() == null) {
                SearchConditionResponse response = SearchConditionResponse.response(searchCondition, null);
                searchConditionResponseList.add(response);
            } else {
                SearchConditionResponse response = SearchConditionResponse.response(searchCondition,
                        jobParentCategoryRepository.findById(searchCondition.getJobParentCategoryId()).orElseThrow(() -> new NotFoundException("not found jpc")).getName());
                searchConditionResponseList.add(response);

            }
        }

        return searchConditionResponseList;
    }

    //공고 조건 수정
    @Transactional
    public void updateSearchCondition(User user, Long searchConditionId, SearchConditionUpdateRequest searchConditionUpdateRequest) {
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));

        searchCondition.update(searchConditionUpdateRequest, user);
    }

    //공고 조건 삭제
    @Transactional
    public void deleteSearchCondition(Long searchConditionId) {
        searchConditionRepository.deleteById(searchConditionId);
    }

    @Transactional
    //공고 조건 상세조회
    public SearchConditionResponse getSearchCondition(Long searchConditionId) {
        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));

        SearchConditionResponse searchConditionResponse = SearchConditionResponse.response(searchCondition,
                jobParentCategoryRepository.findById(searchCondition.getJobParentCategoryId()).isPresent() == true ?
                        jobParentCategoryRepository.findById(searchCondition.getJobParentCategoryId()).get().getName() : null);

        return searchConditionResponse;
    }

    @Transactional
    //공고 전체조회
    public Page<JobOpeningResponse> getJobOpening(Pageable pageable) {
        Page<JobOpening> jobOpeningList = jobOpeningRepository.findAll(pageable);
        List<JobOpeningResponse> jobOpeningResponses = new ArrayList<>();

        for (JobOpening jobOpening : jobOpeningList) {
            if (jobOpening.getIsFinish().equals(IsFinish.F)) {
                jobOpeningResponses.add(JobOpeningResponse.response(jobOpening));
            }
        }

        long total = jobOpeningRepository.findAll().size();

        Page jobOpeningDtoPage = new PageImpl<>(jobOpeningResponses, pageable, total);

        return jobOpeningDtoPage;
    }

    @Transactional
    //공고 전체조회(검색창)
    public Page<JobOpeningResponse> getJobOpeningName(NameSearchConditionRequest nameSearchConditionRequest, Pageable pageable) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");

        List<JobOpening> jobOpeningList = jpaQueryFactory.selectFrom(j)
                .where(
                        nameSearchConditionRequest.getKey().equals("company") ?
                                j.company.name.contains(nameSearchConditionRequest.getWord()) :
                                j.title.contains(nameSearchConditionRequest.getWord())
                                        .and(j.isFinish.eq(IsFinish.F))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<JobOpeningResponse> jobOpeningResponses = jobOpeningList.stream().map(jr -> JobOpeningResponse.response(
                jr
        )).collect(Collectors.toList());

        long total = jobOpeningResponses.size();

        Page jobOpeningDtoPage = new PageImpl<>(jobOpeningResponses, pageable, total);

        return jobOpeningDtoPage;
    }

    @Transactional
    //공고 전체조회(조회수 탑10)
    public List<JobOpeningResponse> getJobOpeningViewDesc() {
        List<JobOpening> jobOpeningList = jobOpeningRepository.findTop10ByOrderByViewsDesc();
        List<JobOpeningResponse> jobOpeningResponses = new ArrayList<>();

        for (JobOpening jobOpening : jobOpeningList) {
            if (jobOpening.getIsFinish().equals(IsFinish.F)) {
                jobOpeningResponses.add(JobOpeningResponse.response(jobOpening));
            }
        }

        return jobOpeningResponses;
    }

    //채용공고 조회(조건포함)
    @Transactional
    public Page<JobOpeningResponse> getJobOpeningConditionList(User user, Long searchConditionId, Pageable pageable) {
        //조건검색을 위한 쿼리DSL 실행
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        SearchCondition searchCondition = searchConditionRepository.findById(searchConditionId).orElseThrow(() -> new NotFoundException("not found searchCondition"));
        List<HopeArea> hopeAreaList = hopeAreaRepository.findBySearchCondition(searchCondition);
        List<HopeJobChild> hopeJobChildList = hopeJobChildRepository.findBySearchCondition(searchCondition);

        List<Gugun> gugunList = new ArrayList<>();
        for (HopeArea hopeArea : hopeAreaList) {
            Gugun gugun = gugunRepository.findById(hopeArea.getGugun().getId()).orElseThrow(() -> new NotFoundException("not found gugun"));
            gugunList.add(gugun);
        }
        ;


        List<JobChildCategory> jobChildCategoryList = new ArrayList<>();
        for (HopeJobChild hopeJobChild : hopeJobChildList) {
            JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(hopeJobChild.getId()).orElseThrow(() -> new NotFoundException("not found jcc"));
            jobChildCategoryList.add(jobChildCategory);
        }

        if (searchCondition.getMinCareer() != 0) booleanBuilder.and(j.minCareer.goe(searchCondition.getMinCareer()));
        if (searchCondition.getMinSalary() != 0) booleanBuilder.and(j.minSalary.goe(searchCondition.getMinSalary()));
        if (searchCondition.getWorkingDay() != 0) booleanBuilder.and(j.workingDay.loe(searchCondition.getWorkingDay()));
        if (searchCondition.getLastEdu() != null) booleanBuilder.and(j.lastEdu.eq(searchCondition.getLastEdu()));
        if (searchCondition.getTypeEmployment() != null)
            booleanBuilder.and(j.typeEmployment.eq(searchCondition.getTypeEmployment()));
        if (searchCondition.getJobParentCategoryId() != null)
            booleanBuilder.and(j.jobChildCategory.parent.id.eq(searchCondition.getJobParentCategoryId()));
        if (gugunList.size() != 0) booleanBuilder.and(j.gugun.in(gugunList));
        if (jobChildCategoryList.size() != 0) booleanBuilder.and(j.jobChildCategory.in(jobChildCategoryList));

        List<JobOpening> jobOpeningList = jpaQueryFactory.selectFrom(j)
                .where(
                        j.isFinish.eq(IsFinish.F).and(booleanBuilder)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //Entity -> Dto변환
        if (jobOpeningList.size() != 0) {
            List<JobOpeningResponse> jobOpeningDtoList =
                    jobOpeningList.stream().map(o -> JobOpeningResponse.response(
                            o
                    )).collect(Collectors.toList());

            long total = jobOpeningDtoList.size();

            Page jobOpeningDtoPage = new PageImpl<>(jobOpeningDtoList, pageable, total);

            return jobOpeningDtoPage;

        } else {
            return null;
        }
    }

    //채용공고 조회(추천공고)
    @Transactional
    public Page<JobOpeningResponse> getJobOpeningRecommendList(User user, Long recommendConditionId, Pageable pageable) {
        //조건검색을 위한 쿼리DSL 실행
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        RecommendCondition rc = recommendConditionRepository.findById(recommendConditionId).orElseThrow(() -> new NotFoundException("not found rc"));

        if (rc.getMinSalary() != 0) booleanBuilder.and(j.minCareer.goe(rc.getMinSalary()));
        if (rc.getWorkingDay() != 0) booleanBuilder.and(j.workingDay.loe(rc.getWorkingDay()));
        if (rc.getGugun() != null) booleanBuilder.and(j.gugun.eq(rc.getGugun()));
        if (rc.getJobParentCategory() != null)
            booleanBuilder.and(j.jobChildCategory.parent.eq(rc.getJobParentCategory()));

        List<JobOpening> jobOpeningList = jpaQueryFactory.selectFrom(j)
                .where(
                        j.isFinish.eq(IsFinish.F).and(booleanBuilder)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        //Entity -> Dto변환
        if (jobOpeningList.size() != 0) {
            List<JobOpeningResponse> jobOpeningDtoList =
                    jobOpeningList.stream().map(o -> JobOpeningResponse.response(
                            o
                    )).collect(Collectors.toList());

            long total = jobOpeningDtoList.size();

            Page jobOpeningDtoPage = new PageImpl<>(jobOpeningDtoList, pageable, total);

            return jobOpeningDtoPage;

        } else {
            return null;
        }
    }

    //채용공고 조회(추천공고, 조회수 탑10)
    @Transactional
    public List<JobOpeningResponse> getJobOpeningListRecommendViewDesc(User user, Long recommendConditionId) {
        //조건검색을 위한 쿼리DSL 실행
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QJobOpening j = new QJobOpening("j");
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        RecommendCondition rc = recommendConditionRepository.findById(recommendConditionId).orElseThrow(() -> new NotFoundException("not found rc"));

        if (rc.getMinSalary() != 0) booleanBuilder.and(j.minCareer.goe(rc.getMinSalary()));
        if (rc.getWorkingDay() != 0) booleanBuilder.and(j.workingDay.loe(rc.getWorkingDay()));
        if (rc.getGugun() != null) booleanBuilder.and(j.gugun.eq(rc.getGugun()));
        if (rc.getJobParentCategory() != null)
            booleanBuilder.and(j.jobChildCategory.parent.eq(rc.getJobParentCategory()));

        List<JobOpening> jobOpeningList = jpaQueryFactory.selectFrom(j)
                .where(
                        j.isFinish.eq(IsFinish.F).and(booleanBuilder)
                )
                .offset(0)
                .limit(10)
                .fetch();

        //Entity -> Dto변환
        if (jobOpeningList.size() != 0) {
            List<JobOpeningResponse> jobOpeningDtoList =
                    jobOpeningList.stream().map(o -> JobOpeningResponse.response(
                            o
                    )).collect(Collectors.toList());


            return jobOpeningDtoList;

        } else {
            return null;
        }
    }

    //채용공고 상세조회
    @Transactional
    public JobOpeningDetailResponse getJobOpening(Long jobOpeningId) {
        Optional<JobOpening> optionalJobOpening = jobOpeningRepository.findById(jobOpeningId);
        optionalJobOpening.orElseThrow(() -> new NotFoundException("not found jobOpening"));

        if (optionalJobOpening.isPresent()) {
            JobOpening jobOpening1 = optionalJobOpening.get();
            jobOpening1.viewUp();
            //연결된 엔티티 매핑
            GugunResponse gugunResponse = GugunResponse.response(jobOpening1.getGugun());
            JobChildCategoryResponse jobChildCategoryResponse = JobChildCategoryResponse.response(jobOpening1.getJobChildCategory());

            //리턴할 Dto 세팅
            JobOpeningDetailResponse jobOpeningDetailResponse = JobOpeningDetailResponse.response(jobOpening1);
            return jobOpeningDetailResponse;
        } else {
            return null;
        }
    }

    //지원하기
    @Transactional
    public Long applyJobOpening(User user, Long jobOpeningId) {
        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException("not found jobOpening"));

        Apply apply = new Apply(user, jobOpening);
        Long id = applyRepository.save(apply).getId();

        return id;
    }

    //지원이력 조회(날짜 오름차순)
    @Transactional
    public List<ApplyResponse> getApplyListDateAsc(User user) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QApply a = new QApply("a");

        List<Apply> applyList = jpaQueryFactory.selectFrom(a)
                .where(a.interviewDate.isNotNull().and(a.user.eq(user)))
                .orderBy(a.interviewDate.asc()).fetch();

        List<ApplyResponse> applyResponseList = applyList.stream().map(res -> ApplyResponse.response(
                res
        )).collect(Collectors.toList());

        return applyResponseList;
    }

    //지원이력 조회
    @Transactional
    public List<ApplyResponse> getApplyList(User user) {
        List<Apply> applyList = applyRepository.findByUser(user);

        List<ApplyResponse> applyResponseList = applyList.stream().map(a -> ApplyResponse.response(
                        a
                )
        ).collect(Collectors.toList());

        return applyResponseList;
    }

    //지원이력 상세조회
    @Transactional
    public ApplyResponse getApply(User user, Long applyId) {
        Apply apply = applyRepository.findById(applyId).orElseThrow(() -> new NotFoundException("not found apply"));

        ApplyResponse applyResponse = ApplyResponse.response(apply);

        return applyResponse;
    }
}
