package com.common.reniors.controller;

import com.common.reniors.common.config.web.LoginUser;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.recommend.RecommendConditionCreateRequest;
import com.common.reniors.dto.recommend.RecommendConditionUpdateRequest;
import com.common.reniors.service.recommend.RecommendConditionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommendcondition")
@Api(tags={"추천 조건 API"})
public class RecommendConditionController {
    private final RecommendConditionService recommendConditionService;

    @PostMapping
    @ApiOperation(value = "추천 조건 생성", notes = "해당 유저의 추천 조건을 생성합니다.")
    public ResponseEntity<?> create(
            @ApiIgnore @LoginUser User user,
            @Valid @RequestBody RecommendConditionCreateRequest request
    ) {
        Long recommendConditionId = recommendConditionService.create(user.getId(), request);
        Map<String, Long> response = new HashMap<>();
        response.put("RecommendCondition", recommendConditionId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "추천 조건 조회", notes = "해당 유저의 추천 조건을 조회합니다.")
    public ResponseEntity<?> read(
            @ApiIgnore @LoginUser User user
    ) {
        return ResponseEntity.ok(recommendConditionService.read(user.getId()));
    }

    @PutMapping
    @ApiOperation(value = "추천 조건 수정", notes = "해당 유저의 추천 조건을 수정합니다.")
    public ResponseEntity<?> update(
            @ApiIgnore @LoginUser User user,
            @Valid @RequestBody RecommendConditionUpdateRequest request
    ) {
        recommendConditionService.update(user.getId(), request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    @ApiOperation(value = "추천 조건 삭제", notes = "해당 유저의 추천 조건을 삭제합니다.")
    public ResponseEntity<Map<String, Long>> delete(
            @ApiIgnore @LoginUser User user
    ) {
        recommendConditionService.delete(user.getId());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}