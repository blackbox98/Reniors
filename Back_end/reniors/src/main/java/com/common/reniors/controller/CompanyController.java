package com.common.reniors.controller;

import com.common.reniors.common.config.data.service.AwsS3Service;
import com.common.reniors.common.config.security.util.JwtUtil;
import com.common.reniors.common.config.web.LoginCompany;
import com.common.reniors.domain.entity.Company;
import com.common.reniors.dto.apply.ApplyResponse;
import com.common.reniors.dto.apply.ApplyUpdateRequest;
import com.common.reniors.dto.company.CompanyCreateRequest;
import com.common.reniors.dto.company.CompanyResponse;
import com.common.reniors.dto.company.CompanyUpdateRequest;
import com.common.reniors.dto.company.CompanyLoginRequest;
import com.common.reniors.dto.jobOpening.*;

import com.common.reniors.dto.user.UserCompanyUseResponse;
import com.common.reniors.dto.user.UserResponse;
import com.common.reniors.service.company.CompanyService;
import com.common.reniors.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Api(tags={"회사 API"})
public class CompanyController {
    private static final String baseURL = "https://reniors.s3.ap-northeast-2.amazonaws.com/";
    private final CompanyService companyService;
    private final UserService userService;
    private final AwsS3Service awsS3Service;

    @GetMapping(path="/idCheck/{companyAppId}")
    @ApiOperation(value = "회사 아아디 중복 검사", notes = "아이디 중복 검사를 진행합니다.")
    public ResponseEntity<?> idCheck(
            @PathVariable String companyAppId
    ) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("res", companyService.idCheck(companyAppId));
        return ResponseEntity.ok(response);
    }

    //회사 회원가입
    @PostMapping(consumes = {"multipart/form-data"})
    @ApiOperation(value = "회사 회원가입", notes = "회사아이디로 회원가입을 한다.")
    public ResponseEntity<?> postCompany(
            @RequestPart(value = "img", required = false) MultipartFile file,
            @RequestPart(value = "data")  CompanyCreateRequest companyCreateRequest) throws Exception {
        String companyProfile = "companyBaseProfile.png";
        if(file != null) {
            companyProfile = awsS3Service.uploadFile(file, "company/");
        }
        Long companyId = companyService.postCompany(companyCreateRequest, baseURL,"company/"+companyProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyId);
    }

    @PostMapping("/login")
    @ApiOperation(value = "회사 로그인", notes = "회사 아이디로 로그인을 한다.")
    public ResponseEntity<?> loginCompany(@RequestBody CompanyLoginRequest companyLoginRequest){
        String accessToken = companyService.loginCompany(companyLoginRequest);
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.AUTHORIZATION,accessToken).build();
    }

    //회사 정보 조회
    @GetMapping()
    @ApiOperation(value = "회사 정보 조회", notes = "회사 정보를 조회한다.")

    public ResponseEntity<?> getCompany(@ApiIgnore @LoginCompany Company company){
        CompanyResponse companyResponse = companyService.getCompany(company);
        return ResponseEntity.status(HttpStatus.OK).body(companyResponse);
    }


    //회사정보 수정
    @PutMapping()
    @ApiOperation(value = "회사 정보수정", notes = "회사 정보를 수정한다.")
    // TODO : image 수정 추가
    public ResponseEntity<?> updateCompany(
            @ApiIgnore @LoginCompany Company company,
            @RequestPart(value = "img", required = false) MultipartFile file,
            @RequestPart(value = "data") CompanyUpdateRequest companyUpdateRequest) throws Exception {
        String companyProfile = company.getCompanyProfile();
        if(file != null && companyUpdateRequest.isChangeProfile()) {
            companyProfile = awsS3Service.uploadFile(file, "company/");
        }
        companyService.updateCompany(company, companyUpdateRequest, baseURL,"company/"+companyProfile);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }


    //회사 탈퇴
    @DeleteMapping()
    @ApiOperation(value = "회사 탈퇴", notes = "회사 아이디 탈퇴한다.")
    public ResponseEntity<?> deleteCompany(@ApiIgnore @LoginCompany Company company){
        awsS3Service.deleteFile(company.getCompanyProfile());
        companyService.deleteCompany(company);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //회사 공고 등록
    @PostMapping("/jobopening")
    @ApiOperation(value = "회사 공고 등록", notes = "회사가 공고를 등록한다.")
    public ResponseEntity<?> postJobOpening(
            @ApiIgnore @LoginCompany Company company,
            @RequestPart(value = "img", required = false) MultipartFile file,
            @RequestPart(value = "data") JobOpeningCreateRequest request){
        String jobOpeningImg = null;
        if(file != null) {
            jobOpeningImg = awsS3Service.uploadFile(file, "jobOpening/");
        }
        Long jobOpeningId = companyService.postJobOpening(company, request, baseURL, "jobOpening/"+jobOpeningImg);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobOpeningId);
    }

    //회사 공고 목록
    @GetMapping("/jobopening")
    @ApiOperation(value = "회사 공고목록", notes = "회사가 올린 공고 목록들을 가져온다.")
    public ResponseEntity<?> getJobOpeningList(@ApiIgnore @LoginCompany Company company){
        List<JobOpeningCompanyResponse> jobOpeningList = companyService.getJobOpeningList(company);
        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningList);
    }

    //회사 공고 상세조회
    @GetMapping("/jobopening/{jobOpeningId}")
    @ApiOperation(value = "회사 공고상세조회", notes = "회사가 올린 공고 하나의 상세정보를 가져온다.")
    public ResponseEntity<?> getJobOpening(@ApiIgnore @LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        JobOpeningCompanyResponse jobOpening = companyService.getJobOpening(company, jobOpeningId);
        return ResponseEntity.status(HttpStatus.OK).body(jobOpening);
    }

    //회사 공고 수정
    @PutMapping("/jobopening/{jobOpeningId}")
    @ApiOperation(value = "회사 공고수정", notes = "회사가 올린 공고를 수정한다.")
    public ResponseEntity<?> updateJobOpening(
            @ApiIgnore @LoginCompany Company company,
            @PathVariable("jobOpeningId") Long jobOpeningId,
            @RequestPart(value = "img", required = false) MultipartFile file,
            @RequestPart(value = "data") JobOpeningUpdateRequest jobOpeningUpdateRequest){

        String jobOpeningImg = null;
        if(file != null) {
            jobOpeningImg = awsS3Service.uploadFile(file, "jobOpening/");
        }
        companyService.updateJobOpening(company, jobOpeningId, jobOpeningUpdateRequest, baseURL, "jobOpening/"+jobOpeningImg);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //회사 공고 삭제
    @DeleteMapping("/jobopening/{jobOpeningId}")
    @ApiOperation(value = "회사 공고삭제", notes = "회사가 올린 공고를 삭제한다.")
    public ResponseEntity<?> deleteJobOpening(@ApiIgnore @LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        companyService.deleteJobOpening(company,jobOpeningId);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //회사 공고 끝내기
    @PutMapping("/jobopening/{jobOpeningId}/finish")
    @ApiOperation(value = "회사 공고끝내기", notes = "회사가 올린 공고를 끝낸다.")
    public ResponseEntity<?> finishJobOpening(@ApiIgnore @LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        companyService.finishJobOpening(company,jobOpeningId);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //회사 공고 과정 수정
    @PutMapping("/progress/{jobOpeningId}")
    @ApiOperation(value = "회사 공고 과정 수정", notes = "회사 공고 과정을 수정 한다.")
    public ResponseEntity<?> updateProgress(@ApiIgnore @LoginCompany Company company,
                                            @PathVariable("jobOpeningId")Long jobOpeningId,
                                            @RequestBody JobOpeningProgressUpdateRequest jobOpeningProgressUpdateRequest){
        companyService.updateProgress(company,jobOpeningId,jobOpeningProgressUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).body("success");

    }

    //회사 공고 지원자 목록
    @GetMapping("/jobopening/{jobOpeningId}/apply")
    @ApiOperation(value = "공고 지원자 목록", notes = "올린 공고의 지원자 목록을 가져온다.")

    public ResponseEntity<?> getApplyList(@ApiIgnore @LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        List<ApplyResponse> applyList = companyService.getappliyList(company, jobOpeningId);
        return ResponseEntity.status(HttpStatus.OK).body(applyList);
    }

    //회사 공고 지원자 목록(날짜 오름차순)
    @GetMapping("/jobopening/{jobOpeningId}/apply/dateAsc")
    @ApiOperation(value = "공고 지원자 목록", notes = "올린 공고의 지원자 목록을 가져온다.")

    public ResponseEntity<?> getApplyListDateAsc(@ApiIgnore @LoginCompany Company company, @PathVariable("jobOpeningId") Long jobOpeningId){
        List<ApplyResponse> applyList = companyService.getappliyListDateAsc(company, jobOpeningId);
        return ResponseEntity.status(HttpStatus.OK).body(applyList);
    }

    //회사 공고 지원자 상세목록
    @GetMapping("/jobopening/{jobOpeningId}/apply/{applyId}")
    @ApiOperation(value = "공고 지원자 상세목록", notes = "올린 공고의 지원자 중 한명의 정보를 가져온다.")
    public ResponseEntity<?> getApplicant( @ApiIgnore @LoginCompany Company company, @PathVariable("applyId") Long applyId){
        ApplyResponse apply = companyService.getapplicant(company, applyId);
        return ResponseEntity.status(HttpStatus.OK).body(apply);
    }

    //회사 공고 지원자 상태수정
    @PutMapping("/jobopening/{jobOpeningId}/apply/{applyId}")
    @ApiOperation(value = "공고 지원자 상태수정", notes = "올린 공고의 지원자들의 정보를 수정한다")
    public ResponseEntity<?> updateApply( @ApiIgnore @LoginCompany Company company,
                                         @PathVariable("applyId") Long applyId,
                                         @RequestBody ApplyUpdateRequest applyUpdateRequest){

        companyService.updateapply(company, applyId, applyUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //회사 공고 지원자 인터뷰 종료하기
    @PutMapping("/jobopening/{jobOpeningId}/apply/{applyId}/finishInterview")
    @ApiOperation(value = "공고 지원자 상태수정", notes = "올린 공고의 지원자들의 정보를 수정한다")
    public ResponseEntity<?> updateApplyFinishInterview( @ApiIgnore @LoginCompany Company company,
                                          @PathVariable("applyId") Long applyId){

        companyService.updateApplyFinishInterview(company, applyId);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @GetMapping("/userdetail/{userId}")
    @ApiOperation(value = "회원 상세 정보 조회", notes = "회원의 상세 정보를 조회한다")
    public ResponseEntity<?> getUserDetails(
            @ApiIgnore @LoginCompany Company company,
            @PathVariable Long userId
    ) {
        UserCompanyUseResponse response = userService.getUserDetails(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
