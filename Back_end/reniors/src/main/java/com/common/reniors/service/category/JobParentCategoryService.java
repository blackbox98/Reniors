package com.common.reniors.service.category;

import com.common.reniors.common.exception.DuplicateException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.category.JobParentCategory;
import com.common.reniors.domain.repository.category.JobParentCategoryRepository;
import com.common.reniors.dto.category.JobParentCategoryCreateRequest;
import com.common.reniors.dto.category.JobParentCategoryResponse;
import com.common.reniors.dto.category.JobParentCategoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotFoundException.CATEGORY_NOT_FOUND;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobParentCategoryService {

    private final JobParentCategoryRepository jobParentCategoryRepository;

    @Transactional
    public void createList(List<JobParentCategoryCreateRequest> requestList){
        requestList.forEach(request -> {
            JobParentCategory jpc = JobParentCategory.create(request.getName(), request.getCode());
            jobParentCategoryRepository.save(jpc);
        });
    }

    @Transactional
    public Long create(JobParentCategoryCreateRequest request) {
        if(jobParentCategoryRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.",request.getName()));
        }
        JobParentCategory jpc = JobParentCategory.create(request.getName(), request.getCode());
        return jobParentCategoryRepository.save(jpc).getId();
    }

    @Transactional
    public void update(Long jpcId, JobParentCategoryUpdateRequest request) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(jpcId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        if(jobParentCategoryRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.",request.getName()));
        }
        jpc.update(request.getName(), request.getCode());
    }

    @Transactional
    public void delete(Long jpcId) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(jpcId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        jobParentCategoryRepository.delete(jpc);
    }

    @Transactional
    public List<JobParentCategoryResponse> getParentList() {
        List<JobParentCategoryResponse> parents = jobParentCategoryRepository.findAll().stream()
                .map(JobParentCategoryResponse::response)
                .collect(Collectors.toList());
        return parents;
    }
}
