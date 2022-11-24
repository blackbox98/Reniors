package com.common.reniors.controller;

import com.common.reniors.common.config.web.LoginUser;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.apply.ApplyResponse;
import com.common.reniors.dto.jobOpening.*;
import com.common.reniors.service.bookmark.BookmarkService;
import com.common.reniors.service.jobOpening.JobOpeningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jobopening")
@RequiredArgsConstructor
@Api(tags={"공고 API"})
public class JobOpeningController {
    private final JobOpeningService jobOpeningService;
    private final BookmarkService bookmarkService;

    //채용공고 조회(조건포함)
    @GetMapping("/search/{searchConditionId}")
    @ApiOperation(value = "공고 조회(조건포함)", notes = "조건검색을 통해 공고를 조회한다.")
    public ResponseEntity<?> getJobOpeningList(
            @ApiIgnore @LoginUser User user,
            @PathVariable("searchConditionId")Long searchConditionId, Pageable pageable){
        Page<JobOpeningResponse> jobOpeningConditionList = jobOpeningService.getJobOpeningConditionList(user,searchConditionId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningConditionList);
    }

    //채용공고 조회(추천공고)
    @GetMapping("/search/recommend/{recommendConditionId}")
    @ApiOperation(value = "공고 조회(추천공고)", notes = "추천검색을 통해 공고를 조회한다.")
    public ResponseEntity<?> getJobOpeningListRecommend(
            @ApiIgnore @LoginUser User user,
            @PathVariable("recommendConditionId")Long recommendConditionId , Pageable pageable){
        Page<JobOpeningResponse> jobOpeningConditionList = jobOpeningService.getJobOpeningRecommendList(user,recommendConditionId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningConditionList);
    }

    //채용공고 조회(추천공고, 조회수 탑10)
    @GetMapping("/search/recommend/{recommendConditionId}/viewsDesc")
    @ApiOperation(value = "공고 조회(추천공고, 조회수 탑10)", notes = "추천리스트 중 조회수 탑10 공고를 조회한다.")
    public ResponseEntity<?> getJobOpeningListRecommendViewDesc(
            @ApiIgnore @LoginUser User user,
            @PathVariable("recommendConditionId")Long recommendConditionId){
        List<JobOpeningResponse> jobOpeningConditionList = jobOpeningService.getJobOpeningListRecommendViewDesc(user, recommendConditionId);
        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningConditionList);
    }

    //공고 전체조회
    @GetMapping("/search")
    @ApiOperation(value = "공고 조회", notes = "전체 공고를 조회한다.")
    public ResponseEntity<?> getJobOpening(Pageable pageable){
        Page<JobOpeningResponse> jobOpeningList = jobOpeningService.getJobOpening(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningList);
    }

    //공고 전체조회(검색창)
    @PostMapping("/search/keyword")
    @ApiOperation(value = "공고 조회(검색창)", notes = "전체 공고중 검색어를 통해 조회한다.")
    public ResponseEntity<?> getJobOpeningName(
            @RequestBody NameSearchConditionRequest nameSearchConditionRequest,
            Pageable pageable){
        Page<JobOpeningResponse> jobOpeningList = jobOpeningService.getJobOpeningName(nameSearchConditionRequest,pageable);
        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningList);
    }

    //공고 전체조회(조회수 탑10)
    @GetMapping("/search/viewsDesc")
    @ApiOperation(value = "공고 조회(조회수 탑10)", notes = "조회수 탑10 공고를 조회한다.")
    public ResponseEntity<?> getJobOpeningViewDesc(){
        List<JobOpeningResponse> jobOpeningList = jobOpeningService.getJobOpeningViewDesc();
        return ResponseEntity.status(HttpStatus.OK).body(jobOpeningList);
    }

    //채용공고 상세조회
    @GetMapping("/{jobOpeningId}")
    @ApiOperation(value = "공고 상세조회", notes = "공고 하나를 조회한다.")
    public ResponseEntity<?> getJobOpening(
            @PathVariable("jobOpeningId") Long jobOpeningId){
        JobOpeningDetailResponse jobOpening = jobOpeningService.getJobOpening(jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(jobOpening);

    }

    //지원하기
    @PostMapping("/{jobOpeningId}/apply")
    @ApiOperation(value = "지원하기", notes = "공고에 지원한다.")
    public ResponseEntity<?> applyJobOpening(
            @PathVariable("jobOpeningId") Long jobOpeningId,
            @ApiIgnore @LoginUser User user){
        Long applyId = jobOpeningService.applyJobOpening(user, jobOpeningId);
        return ResponseEntity.status(HttpStatus.CREATED).body(applyId);
    }

    //지원이력 조회
    @GetMapping("/apply")
    @ApiOperation(value = "지원이력 조회", notes = "지원이력을 조회한다.")
    public ResponseEntity<?> getApplyList(
            @ApiIgnore @LoginUser User user){
        List<ApplyResponse> ApplyList = jobOpeningService.getApplyList(user);

        return ResponseEntity.status(HttpStatus.OK).body(ApplyList);
    }

    //지원이력 조회
    @GetMapping("/apply/dateAsc")
    @ApiOperation(value = "지원이력 조회", notes = "지원이력을 조회한다.")
    public ResponseEntity<?> getApplyListDateAsc(
            @ApiIgnore @LoginUser User user){
        List<ApplyResponse> ApplyList = jobOpeningService.getApplyListDateAsc(user);

        return ResponseEntity.status(HttpStatus.OK).body(ApplyList);
    }

    //지원이력 상세조회
    @GetMapping("/apply/{applyId}")
    @ApiOperation(value = "지원이력 상세조회", notes = "지원이력을 상세조회한다.")
    public ResponseEntity<?> getApplyList(
            @ApiIgnore @LoginUser User user,
            @PathVariable("applyId")Long applyId){
        ApplyResponse apply = jobOpeningService.getApply(user, applyId);

        return ResponseEntity.status(HttpStatus.OK).body(apply);
    }

    //공고 조건 생성(지역제외)
    @PostMapping("/condition")
    @ApiOperation(value = "공고 조건생성", notes = "조건검색에 이용한 조건을 만든다.")
    public ResponseEntity<?> postSearchCondition(
            @ApiIgnore @LoginUser User user,
            @RequestBody SearchConditionCreateRequest searchConditionCreateRequest){
        Long jobOpeningId = jobOpeningService.postSearchCondition(user, searchConditionCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobOpeningId);
    }

    //공고 조건 희망지역 설정
    @PostMapping("/condition/{searchConditionId}/hopearea")
    @ApiOperation(value = "희망지역 조건 생성(다중선택)", notes = "희망하는 지역을 등록한다(다중)")
    public ResponseEntity<?> postHopeArea(
            @PathVariable("searchConditionId")Long searchConditionId,
            @RequestBody HopeAreaCreateRequest hopeAreaCreateRequest){
        Long hopeAreaId = jobOpeningService.postHopeArea(searchConditionId, hopeAreaCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(hopeAreaId);
    }

    @PutMapping("/condition/{searchConditionId}/hopearea/{hopeAreaId}")
    @ApiOperation(value = "희망지역 조건 수정", notes = "희망하는 지역을 수정한다.")
    //공고 조건 희망지역 수정
    public ResponseEntity<?> updateHopeArea(
            @PathVariable("searchConditionId")Long searchConditionId,
            @RequestBody HopeAreaUpdateRequest hopeAreaUpdateRequest,
            @PathVariable("hopeAreaId")Long hopeAreaId){
        jobOpeningService.updateHopeArea(searchConditionId, hopeAreaUpdateRequest,hopeAreaId);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
    //공고 조건 희망지역 삭제
    @DeleteMapping("/condition/{searchConditionId}/hopearea/{hopeAreaId}")
    @ApiOperation(value = "희망지역 조건 삭제", notes = "희망하는 지역을 삭제한다.")
    public ResponseEntity<?> deleteHopeArea(
            @PathVariable("hopeAreaId")Long hopeAreaId
    ){
        jobOpeningService.deleteHopeArea(hopeAreaId);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //공고 조건 희망직무 설정
    @PostMapping("/condition/{searchConditionId}/hopejobchild")
    @ApiOperation(value = "희망직무소분류 조건 생성(다중선택)", notes = "희망하는 직무소분류를 등록한다.(다중선택)")
    public ResponseEntity<?> postHopeJob(@PathVariable("searchConditionId")Long searchConditionId, @RequestBody HopeJobChildCreateRequest hopeJobChildCreateRequest){
        Long hopeJobChildId = jobOpeningService.postHopeJobChild(searchConditionId, hopeJobChildCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(hopeJobChildId);
    }

    //공고 조건 희망직무 수정
    @PutMapping("/condition/{searchConditionId}/hopejobchild/{hopeJobChildId}")

    @ApiOperation(value = "희망직무소분류 조건 수정", notes = "희망하는 직무소분류를 수정한다.")
    public ResponseEntity<?> updateHopeJob(@PathVariable("searchConditionId")Long searchConditionId,
                                           @RequestBody HopeJobChildUpdateRequest hopeJobChildUpdateRequest,
                                           @PathVariable("hopeJobChildId")Long hopeJobChildId){
        jobOpeningService.updateHopeJobChild(searchConditionId,hopeJobChildUpdateRequest,hopeJobChildId);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //공고 조건 희망직무 삭제
    @DeleteMapping("/condition/{searchConditionId}/hopejobchild/{hopeJobChildId}")
    @ApiOperation(value = "희망직무 조건 삭제", notes = "희망하는 직무를 삭제한다.")
    public ResponseEntity<?> deleteHopeJob(@PathVariable("hopeJobChildId")Long hopeJobChildId){
        jobOpeningService.deleteHopeJobChild(hopeJobChildId);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //공고 조건 조회
    @GetMapping("/condition")
    @ApiOperation(value = "공고조건 조회", notes = "공고 조건들을 조회한다.")
    public ResponseEntity<?> getSearchConditionList(
            @ApiIgnore @LoginUser User user
    ){
        List<SearchConditionResponse> searchConditionList = jobOpeningService.getSearchConditionList(user);
        return ResponseEntity.status(HttpStatus.OK).body(searchConditionList);
    }

    //공고 조건 상세조회
    @GetMapping("/condition/{searchConditionId}")
    @ApiOperation(value = "공고조건 상세조회", notes = "공고조건 하나를 상세조회한다.")
    public ResponseEntity<?> getSearchCondition(
            @PathVariable("searchConditionId")Long searchConditionId
    ){
        SearchConditionResponse searchCondition = jobOpeningService.getSearchCondition(searchConditionId);
        return ResponseEntity.status(HttpStatus.OK).body(searchCondition);
    }

    //공고 조건 수정
    @PutMapping("/condition/{searchConditionId}")
    @ApiOperation(value = "공고조건 수정", notes = "공고 조건을 수정한다.")
    public ResponseEntity<?> updateSearchCondition(
            @ApiIgnore @LoginUser User user,
            @PathVariable("searchConditionId")Long searchConditionId,
            @RequestBody SearchConditionUpdateRequest searchConditionUpdateRequest
    ){
        jobOpeningService.updateSearchCondition(user,searchConditionId, searchConditionUpdateRequest);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //공고 조건 삭제
    @DeleteMapping("/condition/{searchConditionId}")
    @ApiOperation(value = "공고조건 삭제", notes = "공고 조건을 삭제한다.")
    public ResponseEntity<?> deleteSearchCondition(
            @PathVariable("searchConditionId")Long searchConditionId
    ){
        jobOpeningService.deleteSearchCondition(searchConditionId);
        return ResponseEntity.status(HttpStatus.OK).body("success");

    }

    // 관심 공고 등록
    @PostMapping("/bookmark/{jobOpenigId}")
    @ApiOperation(value = "관심 공고 등록", notes = "관심 공고를 등록한다.")
    public ResponseEntity<?> createBookmark(
            @ApiIgnore @LoginUser User user,
            @PathVariable Long jobOpenigId
    ){
        Long bookmarkId = bookmarkService.create(user.getId(), jobOpenigId);
        Map<String, Long> response = new HashMap<>();
        response.put("bookmarkId", bookmarkId);
        return ResponseEntity.ok(response);
    }

    // 관심 공고 조회
    @GetMapping("/bookmark")
    @ApiOperation(value = "관심 공고 조회", notes = "관심 공고를 조회한다.")
    public ResponseEntity<?> readBookmarkList(
            @ApiIgnore @LoginUser User user
    ){
        return ResponseEntity.ok(bookmarkService.readList(user.getId()));
    }

    // 관심 공고 상세 조회
    @GetMapping("/bookmark/{bookmarkId}")
    @ApiOperation(value = "관심 공고 상세 조회", notes = "관심 공고를 상세 조회한다.")
    public ResponseEntity<?> readBookmark(
            @PathVariable Long bookmarkId
    ){
        return ResponseEntity.ok(bookmarkService.read(bookmarkId));
    }

    // 관심 공고 해제
    @DeleteMapping("/bookmark/{bookmarkId}")
    @ApiOperation(value = "관심 공고 해제", notes = "관심 공고를 해제한다.")
    public ResponseEntity<Map<String, Long>> deleteBookmark (
            @PathVariable Long bookmarkId
    ){
        bookmarkService.delete(bookmarkId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
