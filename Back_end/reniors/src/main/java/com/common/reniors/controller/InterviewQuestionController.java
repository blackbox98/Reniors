package com.common.reniors.controller;

import com.common.reniors.common.config.web.LoginUser;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.interviewQuestion.AnswerCreateRequest;
import com.common.reniors.dto.interviewQuestion.AnswerUpdateRequest;
import com.common.reniors.dto.interviewQuestion.QuestionCreateRequest;
import com.common.reniors.dto.interviewQuestion.QuestionUpdateRequest;
import com.common.reniors.service.interviewQestion.AnswerService;
import com.common.reniors.service.interviewQestion.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
@Api(tags={"인터뷰 질문 및 답변 API"})
public class InterviewQuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/admin")
    @ApiIgnore
    public void createQuestionList(
            @RequestBody List<QuestionCreateRequest> requestList){
        questionService.createList(requestList);
    }

    //인터뷰 질문 리스트 조회
    @GetMapping
    @ApiOperation(value = "인터뷰 질문 리스트 조회", notes = "인터뷰 예상 질문들을 조회한다.")
    public ResponseEntity<?> getQuestionList(){
        return ResponseEntity.ok(questionService.getQuestionList());
    }

    // 인터뷰 질문 추가
    @PostMapping
    @ApiOperation(value = "인터뷰 질문 추가", notes = "[ROLE_ADMIN]인터뷰 예상 질문을 추가한다.")
    public ResponseEntity<?> createQuestion(
            @Valid @RequestBody QuestionCreateRequest request
            ){
        Long questionId = questionService.create(request);
        Map<String, Long> response = new HashMap<>();
        response.put("questionId", questionId);
        return ResponseEntity.ok(response);
    }
    // 인터뷰 질문 수정
    @PutMapping("/{questionId}")
    @ApiOperation(value = "인터뷰 질문 수정", notes = "[ROLE_ADMIN]인터뷰 예상 질문을 수정한다.")
    public ResponseEntity<?> updateQuestion(
            @PathVariable Long questionId,
            @Valid @RequestBody QuestionUpdateRequest request
            ){
        questionService.update(questionId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 인터뷰 질문 삭제
    @DeleteMapping("/{questionId}")
    @ApiOperation(value = "인터뷰 질문 삭제", notes="[ROLE_ADMIN]인터뷰 예상 질문을 삭제한다.")
    public ResponseEntity<?> deleteQuestion(
            @PathVariable Long questionId
    ){
        questionService.delete(questionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



    // 답변 생성
    @PostMapping("/{questionId}/answers")
    @ApiOperation(value = "인터뷰 답변 작성", notes = "인터뷰 예상 질문에 답변을 작성한다.")
    public ResponseEntity<?> createAnswer(
            @PathVariable Long questionId,
            @ApiIgnore @LoginUser User user,
            @Valid @RequestBody AnswerCreateRequest request
            ){
        Long answerId = answerService.create(questionId, request, user);
        Map<String, Long> response = new HashMap<>();
        response.put("answerId", answerId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{questionId}/answers/check")
    @ApiOperation(value = "인터뷰 답변 작성 여부 조회", notes = "예상 질문에 답변을 작성했는지 확인한다.")
    public ResponseEntity<?> answerCheck(
            @PathVariable("questionId") Long questionId,
            @ApiIgnore @LoginUser User user
    ){
        return ResponseEntity.ok(answerService.answerCheck(questionId, user));
    }
    // 답변 조회
    @GetMapping("/{questionId}/answers")
    @ApiOperation(value = "인터뷰 답변 조회", notes = "예상 질문에 작성한 답변을 조회한다.")
    public ResponseEntity<?> getAnswer(
            @PathVariable("questionId") Long questionId,
            @ApiIgnore @LoginUser User user
    ){
        return ResponseEntity.ok(answerService.getAnswer(questionId, user));
    }

    @GetMapping("/answers/list")
    @ApiOperation(value = "인터뷰 답변 조회", notes = "예상 질문에 작성한 답변을 조회한다.")
    public ResponseEntity<?> getAnswerList(
            @ApiIgnore @LoginUser User user
    ){
        return ResponseEntity.ok(answerService.getAnswerList(user));
    }

    // 답변 수정
    @PutMapping("/{questionId}/answers")
    @ApiOperation(value = "인터뷰 질문 답변 수정", notes = "예상 질문에 작성한 답변을 수정한다.")
    public ResponseEntity<?> updateAnswer(
            @PathVariable("questionId") Long questionId,
            @ApiIgnore @LoginUser User user,
            @Valid @RequestBody AnswerUpdateRequest request
            ){
        answerService.update(questionId, request, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 답변 삭제
    @DeleteMapping("/{questionId}/answers")
    @ApiOperation(value = "인터뷰 질문 답변 삭제", notes = "예상 질문에 작성한 답변을 삭제한다.")
    public ResponseEntity<?> deleteAnswer(
            @PathVariable("questionId") Long questionId,
            @ApiIgnore @LoginUser User user
    ){
        answerService.delete(questionId, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
