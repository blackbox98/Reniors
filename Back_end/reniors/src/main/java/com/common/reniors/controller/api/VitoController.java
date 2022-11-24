package com.common.reniors.controller.api;

import com.common.reniors.common.exception.ApiRequestException;
import com.common.reniors.dto.api.VitoIdCreateRequest;
import com.common.reniors.dto.api.VitoMsgCreateRequest;
import com.common.reniors.service.api.VitoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import org.apache.commons.io.FileUtils;

import javax.validation.Valid;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

@RestController
@RequestMapping("/vito")
@RequiredArgsConstructor
@Api(tags={"STT API"})
public class VitoController {
    private final VitoService vitoService;
    @PostMapping("/videoId")
    @ApiOperation(value = "vito 일반stt 비디오 등록", notes = "vito 일반stt에 비디오를 등록하고 아이디를 리턴합니다.")
    public ResponseEntity<?> getVideoId(@RequestBody VitoIdCreateRequest vitoIdCreateRequest){
        String videoId = vitoService.getVideoId(vitoIdCreateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(videoId);
    }

    @PostMapping("/audioMsg")
    @ApiOperation(value = "vito 일반stt 자막", notes = "vito 일반stt 자막을 리턴합니다.")
    public ResponseEntity<?> getAudioMsg(@RequestBody VitoMsgCreateRequest vitoMsgCreateRequest){
        String audioMsg = vitoService.getAudioMsg(vitoMsgCreateRequest);

        return ResponseEntity.status(HttpStatus.OK).body(audioMsg);
    }
}
