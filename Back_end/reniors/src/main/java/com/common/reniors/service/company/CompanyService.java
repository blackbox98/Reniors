package com.common.reniors.service.company;

import com.common.reniors.common.config.security.util.JwtUtil;
import com.common.reniors.common.exception.DuplicateException;
import com.common.reniors.common.exception.NotAuthException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.common.exception.NotMatchException;
import com.common.reniors.domain.entity.Apply;
import com.common.reniors.domain.entity.Company;
import com.common.reniors.domain.entity.JobOpening;
import com.common.reniors.domain.entity.QApply;
import com.common.reniors.domain.entity.category.Gugun;
import com.common.reniors.domain.entity.category.JobChildCategory;
import com.common.reniors.domain.repository.ApplyRepository;
import com.common.reniors.domain.repository.CompanyRepository;
import com.common.reniors.domain.repository.JobOpeningRepository;
import com.common.reniors.domain.repository.category.GugunRepository;
import com.common.reniors.domain.repository.category.JobChildCategoryRepository;
import com.common.reniors.dto.apply.ApplyResponse;
import com.common.reniors.dto.apply.ApplyUpdateRequest;
import com.common.reniors.dto.company.CompanyCreateRequest;
import com.common.reniors.dto.company.CompanyLoginRequest;
import com.common.reniors.dto.company.CompanyResponse;
import com.common.reniors.dto.company.CompanyUpdateRequest;
import com.common.reniors.dto.jobOpening.JobOpeningCompanyResponse;
import com.common.reniors.dto.jobOpening.JobOpeningCreateRequest;
import com.common.reniors.dto.jobOpening.JobOpeningProgressUpdateRequest;
import com.common.reniors.dto.jobOpening.JobOpeningUpdateRequest;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotAuthException.COMPANY_NO_AUTH;
import static com.common.reniors.common.exception.NotFoundException.*;
import static com.common.reniors.common.exception.NotMatchException.PASSWORD_NOT_MATCH;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final JobOpeningRepository jobOpeningRepository;
    private final CompanyRepository companyRepository;
    private final GugunRepository gugunRepository;
    private final JobChildCategoryRepository jobChildCategoryRepository;
    private final ApplyRepository applyRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    @PersistenceContext
    EntityManager em;

    @Transactional
    public Boolean idCheck(String companyAppId) {
        if (companyRepository.findByCompanyAppId(companyAppId).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    //회사 회원가입
    @Transactional
    public Long postCompany(CompanyCreateRequest request, String baseURL ,String companyProfile){
        if (companyRepository.findByCompanyNum(request.getCompanyNum()).isPresent()) {
            throw new DuplicateException(String.format("%s은 이미 가입된 기업입니다.", request.getName()));
        } else {
        Company company = Company.create(
                request.getName(),
                request.getCompanyAppId(),
                passwordEncoder.encode(request.getCompanyAppPwd()),
                request.getEstablishedAt(),
                request.getCompanyUrl(),
                request.getAddress(),
                request.getCompanyNum(),
                request.getCompanyPhone(),
                request.getRepresentative(),
                baseURL,
                companyProfile,
                request.getRepresentativePhone(),
                request.getTypeCompany()
        );
        return companyRepository.save(company).getId();
        }
    }

    @Transactional
    public String loginCompany(CompanyLoginRequest request){
        Optional<Company> optionalCompany = companyRepository.findByCompanyAppId(request.getCompanyAppId());
        if (!optionalCompany.isPresent()){
            throw new NotFoundException(USER_NOT_FOUND);
        }else {
            if (passwordEncoder.matches(request.getCompanyAppPwd(), optionalCompany.get().getCompanyAppPwd())){
                return jwtUtil.createToken(optionalCompany.get().getId(), "company");
            }else {
                throw new NotMatchException(PASSWORD_NOT_MATCH);
            }
        }
    }

    //회사 상세정보
    @Transactional
    public CompanyResponse getCompany(Company company){
        Company company1 = companyRepository.findById(company.getId()).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        CompanyResponse response = CompanyResponse.response(company1);
        return response;
    }

    //회사 정보수정
    @Transactional
    public void updateCompany(Company company, CompanyUpdateRequest request, String baseURL, String companyProfile){
        company.update(request, baseURL, companyProfile);
    }

    //회사 탈퇴
    @Transactional
    public void deleteCompany(Company company){
        companyRepository.deleteById(company.getId());
    }

    //회사 공고 등록
    @Transactional
    public Long postJobOpening(Company company, JobOpeningCreateRequest request, String baseURL, String jobOpeningImg){
        Gugun gugun = gugunRepository.findById(request.getGugunId()).orElseThrow(() -> new NotFoundException("not found gugun"));
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(request.getJobChildCategoryId()).orElseThrow(() -> new NotFoundException("not found jcc"));
        JobOpening jobOpening = new JobOpening(request,baseURL, jobOpeningImg, company, gugun, jobChildCategory);
        Long id = jobOpeningRepository.save(jobOpening).getId();
        return id;
    }

    //회사 공고 목록
    @Transactional
    public List<JobOpeningCompanyResponse> getJobOpeningList(Company company){
            List<JobOpening> jobOpeningList = jobOpeningRepository.findByCompany(company);
            List<JobOpeningCompanyResponse> jobOpeningResponses = jobOpeningList.stream().map(j->JobOpeningCompanyResponse.response(
                    j
            )).collect(Collectors.toList());
            return jobOpeningResponses;
    }

    //회사 공고 상세조회
    @Transactional
    public JobOpeningCompanyResponse getJobOpening(Company company, Long jobOpeningId){
        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);
        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException("not found jobOpening"));

        JobOpeningCompanyResponse jobOpeningCompanyResponse = JobOpeningCompanyResponse.response(jobOpening);
        return jobOpeningCompanyResponse;
    }

    //회사 공고 수정
    @Transactional
    public void updateJobOpening(Company company, Long jobOpeningId, JobOpeningUpdateRequest jobOpeningUpdateRequest, String baseURL, String jobOpeningImg){
        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);
        Gugun gugun = gugunRepository.findById(jobOpeningUpdateRequest.getGugunId()).orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));
        JobChildCategory jobChildCategory = jobChildCategoryRepository.findById(jobOpeningUpdateRequest.getJobChildCategoryId()).orElseThrow(() -> new NotFoundException("not found jcc"));
        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException(JOB_OPENING_NOT_FOUND));
        jobOpening.update(jobOpeningUpdateRequest,baseURL, jobOpeningImg, gugun, jobChildCategory);
    }

    //회사 공고 삭제
    @Transactional
    public void deleteJobOpening(Company company, Long jobOpeningId){
        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);
        jobOpeningRepository.deleteById(jobOpeningId);
    }

    //회사 공고 끝내기
    @Transactional
    public void finishJobOpening(Company company, Long jobOpeningId){
        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);

        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException("not found jobopening"));
        jobOpening.finish();
    }

    //회사 공고 과정 수정
    @Transactional
    public void updateProgress(Company company, Long jobOpeningId, JobOpeningProgressUpdateRequest jobOpeningProgressUpdateRequest){
        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);

        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException("not found jobopening"));
        jobOpening.updateProgress(jobOpeningProgressUpdateRequest.getJobOpeningProcess());
    }

    //회사 공고 지원자 목록
    @Transactional
    public List<ApplyResponse> getappliyList(Company company, Long jobOpeningId){
        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);
        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException("not found Apply"));
        List<Apply> applyList = applyRepository.findByJobOpening(jobOpening);
        List<ApplyResponse> applyResponseList = applyList.stream().map(a->ApplyResponse.response(
                a
        )).collect(Collectors.toList());
        return  applyResponseList;
    }

    //회사 공고 지원자 목록(날짜 오름차순)
    @Transactional
    public List<ApplyResponse> getappliyListDateAsc(Company company, Long jobOpeningId){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em);
        QApply a = new QApply("a");

        if (company.getId() != jobOpeningRepository.findById(jobOpeningId).get().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);
        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException("not found Apply"));
        List<Apply> applyList = jpaQueryFactory.selectFrom(a)
                .where(a.jobOpening.eq(jobOpening),
                        a.interviewDate.isNotNull())
                .orderBy(a.interviewDate.asc()).fetch();

        List<ApplyResponse> applyResponseList = applyList.stream().map(res->ApplyResponse.response(
                res
        )).collect(Collectors.toList());
        return  applyResponseList;
    }

    //회사 공고 지원자 상세목록
    @Transactional
    public ApplyResponse getapplicant(Company company, Long applyId){
        if(company.getId() != applyRepository.findById(applyId).get().getJobOpening().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);
        Apply apply = applyRepository.findById(applyId).orElseThrow(() -> new NotFoundException("not found Apply"));
        ApplyResponse applyResponse = ApplyResponse.response(apply);

        return applyResponse;
    }

    //회사 공고 지원자 상태수정
    @Transactional
    public void updateapply(Company company, Long applyId, ApplyUpdateRequest applyUpdateRequest){
        if(company.getId() != applyRepository.findById(applyId).get().getJobOpening().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);
        Apply apply = applyRepository.findById(applyId).orElseThrow(() -> new NotFoundException("not found Apply"));

        apply.update(applyUpdateRequest,apply.getUser(), apply.getJobOpening());
    }

    //회사 공고 지원자 인터뷰 종료하기
    @Transactional
    public void updateApplyFinishInterview(Company company, Long applyId){
        if(company.getId() != applyRepository.findById(applyId).get().getJobOpening().getCompany().getId())
            throw new NotAuthException(COMPANY_NO_AUTH);
        Apply apply = applyRepository.findById(applyId).orElseThrow(() -> new NotFoundException("not found Apply"));
        apply.finishInterview();
    }
}
