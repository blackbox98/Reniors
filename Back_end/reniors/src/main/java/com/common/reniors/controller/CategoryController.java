package com.common.reniors.controller;

import com.common.reniors.dto.category.*;
import com.common.reniors.service.category.GugunService;
import com.common.reniors.service.category.JobChildCategoryService;
import com.common.reniors.service.category.JobParentCategoryService;
import com.common.reniors.service.category.SidoService;
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
@RequiredArgsConstructor
@RequestMapping("/categories")
@Api(tags={"카테고리 API"})
public class CategoryController {
    private final SidoService sidoService;
    private final GugunService gugunService;
    private final JobChildCategoryService jobChildCategoryService;
    private final JobParentCategoryService jobParentCategoryService;


    @PostMapping("/admin/sido")
    @ApiIgnore
    public void createSidoList(
            @RequestBody List<SidoCreateRequest> requestList){
        sidoService.createList(requestList);
    }
    @PostMapping("/admin/gugun")
    @ApiIgnore
    public void createGugunList(
            @RequestBody List<GugunCreateRequest> requestList){
        gugunService.createList(requestList);
    }
    @PostMapping("/admin/jpc")
    @ApiIgnore
    public void createJPCList(
            @RequestBody List<JobParentCategoryCreateRequest> requestList){
        jobParentCategoryService.createList(requestList);
    }
    @PostMapping("/admin/jcc")
    @ApiIgnore
    public void createJCCList(
            @RequestBody List<JobChildCategoryCreateRequest> requestList){
        jobChildCategoryService.createList(requestList);
    }

    @PostMapping("/sido")
    @ApiOperation(value = "시/도 생성", notes = "[ROLE_ADMIN]시/도를 생성한다.")
    public ResponseEntity<?> createSido(
            @Valid @RequestBody SidoCreateRequest request){
        Long sidoId = sidoService.create(request);
        Map<String, Long> response = new HashMap<>();
        response.put("sidoId", sidoId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sido")
    @ApiOperation(value = "시/도 조회", notes = "시/도 를 조회한다.")
    public ResponseEntity<?> readSido(){
        return ResponseEntity.ok(sidoService.getSidoList());
    }

    @PutMapping("/sido/{sidoId}")
    @ApiOperation(value = "시/도 수정", notes = "[ROLE_ADMIN]시/도를 수정한다.")
    public ResponseEntity<?> updateSido(
            @PathVariable Long sidoId,
            @Valid @RequestBody SidoUpdateRequest request){
        sidoService.update(sidoId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/sido/{sidoId}")
    @ApiOperation(value = "시/도 삭제", notes = "[ROLE_ADMIN]시/도를 삭제한다.")
    public ResponseEntity<?> deleteSido(
            @PathVariable Long sidoId){
        sidoService.delete(sidoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/sido/{sidoId}/gugun")
    @ApiOperation(value = "구/군 생성", notes = "[ROLE_ADMIN]구/군을 생성한다.")
    public ResponseEntity<?> createGugun(
            @PathVariable Long sidoId,
            @Valid @RequestBody GugunCreateRequest request){
        Long gugunId = gugunService.create(sidoId, request);
        gugunService.create(sidoId, request);
        Map<String, Long> response = new HashMap<>();
        response.put("gugunId", gugunId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sido/{sidoId}/gugun")
    @ApiOperation(value = "구/군 조회", notes = "구/군을 조회한다.")
    public ResponseEntity<?> readGugun(
            @PathVariable Long sidoId){
        return ResponseEntity.ok(gugunService.getGugunList(sidoId));
    }

    @PutMapping("/sido/{sidoId}/gugun/{gugunId}")
    @ApiOperation(value = "구/군 수정", notes = "[ROLE_ADMIN]구/군을 수정한다.")
    public ResponseEntity<?> updateGugun(
            @PathVariable("sidoId") Long sidoId,
            @PathVariable("gugunId") Long gugunId,
            @Valid @RequestBody GugunUpdateRequest request){
        gugunService.update(gugunId,request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/sido/{sidoId}/gugun/{gugunId}")
    @ApiOperation(value = "구/군 삭제", notes = "[ROLE_ADMIN]구/군을 삭제한다.")
    public ResponseEntity<?> deleteGugun(
            @PathVariable Long gugunId){
        gugunService.delete(gugunId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/parent")
    @ApiOperation(value = "직무 대분류 생성", notes = "[ROLE_ADMIN]직무 대분류를 생성한다.")
    public ResponseEntity<?> createParent(
            @Valid @RequestBody JobParentCategoryCreateRequest request){
        Long parentId = jobParentCategoryService.create(request);
        Map<String, Long> response = new HashMap<>();
        response.put("parentId", parentId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/parent")
    @ApiOperation(value = "직무 대분류 조회", notes = "직무 대분류를 조회한다.")
    public ResponseEntity<?> readParent(){
        return ResponseEntity.ok(jobParentCategoryService.getParentList());
    }
    @PutMapping("/parent/{parentId}")
    @ApiOperation(value = "직무 대분류 수정", notes = "[ROLE_ADMIN]직무 대분류를 수정한다.")
    public ResponseEntity<?> updateParent(
            @PathVariable Long parentId,
            @Valid @RequestBody JobParentCategoryUpdateRequest request){
        jobParentCategoryService.update(parentId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/parent/{parentId}")
    @ApiOperation(value = "직무 대분류 삭제", notes = "[ROLE_ADMIN]직무 대분류를 삭제한다.")
    public ResponseEntity<?> deleteParent(
            @PathVariable Long parentId){
        jobParentCategoryService.delete(parentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/parent/{parentId}/child")
    @ApiOperation(value = "직무 소분류 생성", notes = "[ROLE_ADMIN]직무 소분류를 생성한다.")
    public ResponseEntity<?> createChild(
            @PathVariable Long parentId,
            @Valid @RequestBody JobChildCategoryCreateRequest request){
        Long childId = jobChildCategoryService.create(parentId, request);
        Map<String, Long> response = new HashMap<>();
        response.put("childId", childId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/parent/{parentId}/child")
    @ApiOperation(value = "직무 소분류 조회", notes = "직무 소분류를 조회한다.")
    public ResponseEntity<?> readChild(
            @PathVariable Long parentId){
        return ResponseEntity.ok(jobChildCategoryService.getChildList(parentId));
    }
    @PutMapping("/parent/{parentId}/child/{childId}")
    @ApiOperation(value = "직무 소분류 수정", notes = "[ROLE_ADMIN]직무 소분류를 수정한다.")
    public ResponseEntity<?> updateChild(
            @PathVariable("parentId") Long parentId,
            @PathVariable("childId") Long childId,
            @Valid @RequestBody JobChildCategoryUpdateRequest request){
        jobChildCategoryService.update(childId, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/parent/{parentId}/child/{childId}")
    @ApiOperation(value = "직무 소분류 삭제", notes = "[ROLE_ADMIN]직무 소분류를 삭제한다.")
    public ResponseEntity<?> deleteChild(
            @PathVariable("parentId") Long parentId,
            @PathVariable("childId") Long childId){
        jobChildCategoryService.delete(childId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
