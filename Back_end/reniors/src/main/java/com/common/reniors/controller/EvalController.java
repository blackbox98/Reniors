package com.common.reniors.controller;

import com.common.reniors.common.config.web.LoginCompany;
import com.common.reniors.domain.entity.Company;
import com.common.reniors.dto.eval.*;
import com.common.reniors.service.eval.EvalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/eval")
@RequiredArgsConstructor
@Api(tags={"평가 API"})
public class EvalController {
    private final EvalService evalService;

    //평가 질문 등록
    @PostMapping()
    @ApiOperation(value = "평가 질문 등록", notes = "평가 폼에 대한 질문을 등록한다.")
    public ResponseEntity<?> postEvalQuestion(@ApiIgnore @LoginCompany Company company, @RequestBody EvalQuestionCreateRequest evalQuestionCreateRequest){
        Long evalQuestionId = evalService.postEvalQuestion(company, evalQuestionCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(evalQuestionId);
    }

    //평가 질문 조회

    @GetMapping("/search/{jobOpeningId}")
    @ApiOperation(value = "평가 질문 조회", notes = "평가 폼에 대한 질문을 조회한다.")
    public ResponseEntity<?> getEvalQuestionList(@ApiIgnore @LoginCompany Company company,@PathVariable("jobOpeningId")Long jobOpeningId){
        List<EvalQuestionResponse> evalQuestionList = evalService.getEvalQuestionList(company, jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(evalQuestionList);
    }

    //평가 질문 상세조회
    @GetMapping("/{evalQuestionId}")
    @ApiOperation(value = "평가 질문 상세조회", notes = "평가 폼에 대한 질문하나를 조회한다.")
    public ResponseEntity<?> getEvalQuestion(@ApiIgnore @LoginCompany Company company,
                                             @PathVariable("evalQuestionId")Long evalQuestionId){
        EvalQuestionResponse evalQuestion = evalService.getEvalQuestion(company, evalQuestionId);

        return ResponseEntity.status(HttpStatus.OK).body(evalQuestion);
    }

    //평가 질문 수정
    @PutMapping("/{evalQuestionId}")
    @ApiOperation(value = "평가 질문 수정", notes = "평가 폼에 대한 질문 하나를 수정한다.")
    public ResponseEntity<?> updateEvalQuestion(@ApiIgnore @LoginCompany Company company,
                                                @PathVariable("evalQuestionId")Long evalQuestionId,
                                                @RequestBody EvalQuestionUpdateRequest evalQuestionUpdateRequest){
        evalService.updateEvalQuestion(company,evalQuestionId, evalQuestionUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //평가 질문 삭제
    @DeleteMapping("/{evalQuestionId}")
    @ApiOperation(value = "평가 질문 삭제", notes = "평가 폼에 대한 질문 하나를 삭제한다.")
    public ResponseEntity<?> deleteEvalQuestion(@ApiIgnore @LoginCompany Company company, @PathVariable("evalQuestionId")Long evalQuestionId){
        evalService.deleteEvalQuestion(company,evalQuestionId);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    //유저 평가 입력
    @PostMapping("/{jobOpeningId}/usereval/{userId}")
    @ApiOperation(value = "유저 평가 입력", notes = "유저에 대한 평가 입력")
    public ResponseEntity<?> postUserEval(@ApiIgnore @LoginCompany Company company,
                                          @RequestBody UserEvalCreateRequest userEvalCreateRequest,
                                          @PathVariable("userId")Long userId){
        Long userEvalId = evalService.postUserEval(company, userEvalCreateRequest, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(userEvalId);
    }
    //유저 평가 조회
    @GetMapping("/{jobOpeningId}/usereval/{userId}")
    @ApiOperation(value = "유저 평가 조회", notes = "유저에 대한 평가 조회")
    public ResponseEntity<?> getUserEvalList(@ApiIgnore @LoginCompany Company company,  @PathVariable("userId")Long userId, @PathVariable("jobOpeningId")Long jobOpeningId){
        List<UserEvalResponse> userEvalList = evalService.getUserEvalList(company, userId, jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body(userEvalList);
    }

    //유저 평가 삭제
    @DeleteMapping("/{jobOpeningId}/usereval/{userId}")
    @ApiOperation(value = "유저 평가 삭제", notes = "유저에 대한 평가 삭제")
    public ResponseEntity<?> deleteUserEval(@ApiIgnore @LoginCompany Company company,  @PathVariable("userId")Long userId, @PathVariable("jobOpeningId")Long jobOpeningId){
        evalService.deleteUserEval(company, userId, jobOpeningId);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }


}
