package com.common.reniors.service.category;

import com.common.reniors.common.exception.DuplicateException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.category.JobChildCategory;
import com.common.reniors.domain.entity.category.JobParentCategory;
import com.common.reniors.domain.repository.category.JobChildCategoryRepository;
import com.common.reniors.domain.repository.category.JobParentCategoryRepository;
import com.common.reniors.dto.category.JobChildCategoryCreateRequest;
import com.common.reniors.dto.category.JobChildCategoryResponse;
import com.common.reniors.dto.category.JobChildCategoryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotFoundException.CATEGORY_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JobChildCategoryService{

    private final JobParentCategoryRepository jobParentCategoryRepository;
    private final JobChildCategoryRepository jobChildCategoryRepository;


    @Transactional
    public void createList(List<JobChildCategoryCreateRequest> requestList){
        requestList.forEach(request -> {
            JobParentCategory jpc = jobParentCategoryRepository.findByCode(request.getCode())
                    .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
            JobChildCategory jcc = JobChildCategory.create(request.getName(), request.getCode(), jpc);
            jobChildCategoryRepository.save(jcc);
        });
    }
    @Transactional
    public Long create(Long jpcId, JobChildCategoryCreateRequest request) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(jpcId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        if(jobChildCategoryRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.",request.getName()));
        }
        JobChildCategory jcc = JobChildCategory.create(request.getName(),request.getCode(), jpc);
        return jobChildCategoryRepository.save(jcc).getId();
    }

    @Transactional
    public void update(Long jccId, JobChildCategoryUpdateRequest request) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(request.getParentId())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        JobChildCategory jcc = jobChildCategoryRepository.findById(jccId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        if(jobChildCategoryRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리입니다.", request.getName()));
        }
        jcc.update(request.getName(), jcc.getCode(), jpc);
    }

    @Transactional
    public void delete(Long jccId) {
        JobChildCategory jcc = jobChildCategoryRepository.findById(jccId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        jobChildCategoryRepository.delete(jcc);
    }

    @Transactional
    public List<JobChildCategoryResponse> getChildList(Long jpcId) {
        JobParentCategory jpc = jobParentCategoryRepository.findById(jpcId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        List<JobChildCategoryResponse> childs = jobChildCategoryRepository.findByParent(jpc).stream()
                .map(JobChildCategoryResponse::response)
                .collect(Collectors.toList());
        return childs;
    }
}
