package com.common.reniors.controller;

import com.common.reniors.common.config.web.LoginUser;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.recording.RecordingCreateRequest;
import com.common.reniors.dto.recording.RecordingVideoCreateRequest;
import com.common.reniors.service.recording.RecordingService;
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
@RequestMapping("/recording")
@RequiredArgsConstructor
@Api(tags={"녹화본 API"})
public class RecordingController {
    private static final String baseURL = "https://reniors.s3.ap-northeast-2.amazonaws.com/";
    private final RecordingService recordingService;

    @PostMapping
    @ApiOperation(value = "녹화본 저장", notes = "녹화본을 저장한다.")
    public ResponseEntity<?> createRecording(
            @ApiIgnore @LoginUser User user,
            @Valid @RequestBody RecordingCreateRequest request
    ){
        Long recordingId = recordingService.create(request, user);
        Map<String, Long> response = new HashMap<>();
        response.put("recordingId", recordingId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    @ApiOperation(value = "녹화본 리스트 조회", notes = "저장한 녹화본들을 조회한다.")
    public ResponseEntity<?> getRecordingList(
            @ApiIgnore @LoginUser User user
    ){
        return ResponseEntity.ok(recordingService.getRecordingList(user));
    }

    @DeleteMapping("/{recordingId}")
    @ApiOperation(value = "녹화본을 삭제한다.", notes = "저장된 녹화본을 삭제한다.")
    public ResponseEntity<?> deleteRecording(
            @ApiIgnore @LoginUser User user,
            @PathVariable Long recordingId
    ){
        recordingService.delete(recordingId, user);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{recordingId}/videoId")
    @ApiOperation(value = "녹화본의 vito 아이디를 저장한다.", notes = "녹화본의 vito 아이디를 저장한다.")
    public ResponseEntity<?> updateVideoId(@ApiIgnore @LoginUser User user,
                                           @PathVariable Long recordingId,
                                           @RequestBody RecordingVideoCreateRequest recordingVideoCreateRequest){
        recordingService.updateVideoId(user,recordingId,recordingVideoCreateRequest);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
