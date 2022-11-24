package com.common.reniors.service.category;

import com.common.reniors.common.exception.DuplicateException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.category.Gugun;
import com.common.reniors.domain.entity.category.Sido;
import com.common.reniors.domain.repository.category.GugunRepository;
import com.common.reniors.domain.repository.category.SidoRepository;
import com.common.reniors.dto.category.GugunCreateRequest;
import com.common.reniors.dto.category.GugunResponse;
import com.common.reniors.dto.category.GugunUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotFoundException.CATEGORY_NOT_FOUND;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GugunService{

    private final SidoRepository sidoRepository;
    private final GugunRepository gugunRepository;


    @Transactional
    public void createList(List<GugunCreateRequest> requestList){
        requestList.forEach(request -> {
            Sido sido = sidoRepository.findByCode(Long.parseLong(request.getCode().toString().substring(0,2)+"00000000"))
                    .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
            Gugun gugun = Gugun.create(request.getName(), request.getCode(), sido);
            gugunRepository.save(gugun);
        });
    }
    @Transactional
    public Long create(Long sidoId, GugunCreateRequest request) {
        Sido sido = sidoRepository.findByCode(Long.parseLong(request.getCode().toString().substring(0,2)+"00000000"))
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));

        if(gugunRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리 입니다.", request.getName()));
        }
        Gugun gugun = Gugun.create(request.getName(), request.getCode(), sido);
        return gugunRepository.save(gugun).getId();
    }

    @Transactional
    public void update(Long gugunId, GugunUpdateRequest request) {
        Sido sido = sidoRepository.findByCode(request.getSidoCode())
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        Gugun gugun = gugunRepository.findById(gugunId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        if(gugunRepository.findByName(request.getName()).isPresent()){
            throw new DuplicateException(String.format("%s는 이미 존재하는 카테고리 입니다.", request.getName()));
        }
        gugun.update(request.getName(), request.getCode(), sido);
    }

    @Transactional
    public void delete(Long gugunId) {
        Gugun gugun = gugunRepository.findById(gugunId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        gugunRepository.delete(gugun);
    }

    @Transactional
    public List<GugunResponse> getGugunList(Long sidoId) {
        Sido sido = sidoRepository.findById(sidoId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        List<GugunResponse> guguns = gugunRepository.findBySido(sido).stream()
                .map(GugunResponse::response)
                .collect(Collectors.toList());
        return guguns;
    }
}
