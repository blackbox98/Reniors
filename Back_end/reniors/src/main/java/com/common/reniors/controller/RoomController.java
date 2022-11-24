package com.common.reniors.controller;

import com.common.reniors.common.config.web.LoginCompany;
import com.common.reniors.common.config.web.LoginUser;
import com.common.reniors.domain.entity.Company;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.dto.room.RoomCreateRequest;
import com.common.reniors.dto.room.RoomResponse;
import com.common.reniors.dto.room.RoomUpdateRequest;
import com.common.reniors.service.room.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
@Api(tags={"화상면접방 API"})
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/company")
    @ApiOperation(value = "화상면접방 생성", notes = "화상면접방을 생성한다")
    public ResponseEntity<?> postRoom(@ApiIgnore @LoginCompany Company company,
                                      @RequestBody RoomCreateRequest roomCreateRequest){
        Long roomId = roomService.postRoom(company, roomCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(roomId);
    }

    @PutMapping("/company")
    @ApiOperation(value = "화상면접방 수정", notes = "화상면접방을 수정한다")
    public ResponseEntity<?> updateRoom(@ApiIgnore @LoginCompany Company company,
                                        @RequestBody RoomUpdateRequest roomUpdateRequest){
        roomService.updateRoom(company, roomUpdateRequest);

        return ResponseEntity.status(HttpStatus.OK).body("success");
    }

    @GetMapping("/user")
    @ApiOperation(value = "화상면접방 기져오기(유저)", notes = "화상면접방을 가져온다(유저)")
    public ResponseEntity<?> updateRoom(@ApiIgnore @LoginUser User user){
        List<RoomResponse> roomList = roomService.getRoomList(user);

        return ResponseEntity.status(HttpStatus.OK).body(roomList);
    }

}
