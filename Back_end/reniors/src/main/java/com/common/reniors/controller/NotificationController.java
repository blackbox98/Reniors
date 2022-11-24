package com.common.reniors.controller;

import com.common.reniors.common.config.web.LoginUser;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.notification.NotificationCreateRequest;
import com.common.reniors.service.notification.NotificationService;
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
@RequestMapping("/notification")
@RequiredArgsConstructor
@Api(tags={"알림 API"})
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    @ApiOperation(value = "알림 추가", notes = "알림을 추가합니다.")
    public ResponseEntity<?> create(
            @Valid @RequestBody NotificationCreateRequest request
    ) {
        Long notificationId = notificationService.create(request);
        Map<String, Long> response = new HashMap<>();
        response.put("notificationId", notificationId);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation(value = "알림 목록 조회", notes = "알림 목록을 조회합니다.")
    public ResponseEntity<?> readList(
            @ApiIgnore @LoginUser User user
    ) {
        return ResponseEntity.ok(notificationService.readList(user.getId()));
    }

    @GetMapping("/{notificationId}")
    @ApiOperation(value = "알림 읽음 상태 변경", notes = "알림을 읽은 상태로 변경합니다.")
    public ResponseEntity<Map<String, Long>> readList(
            @ApiIgnore @LoginUser User user,
            @PathVariable Long notificationId
    ) {
        notificationService.read(notificationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{notificationId}")
    @ApiOperation(value = "알림 삭제", notes = "알림 삭제합니다.")
    public ResponseEntity<Map<String, Long>> delete(
            @ApiIgnore @LoginUser User user,
            @PathVariable Long notificationId
    ) {
        notificationService.delete(notificationId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
