package com.common.reniors.service.resume;

import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.resume.CareerDetail;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.resume.CareerDetailRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.resume.CareerDetailCreateRequest;
import com.common.reniors.dto.resume.CareerDetailResponse;
import com.common.reniors.dto.resume.CareerDetailUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotFoundException.CAREER_DETAIL_NOT_FOUND;
import static com.common.reniors.common.exception.NotFoundException.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CareerDetailService {
    private final UserRepository userRepository;
    private final CareerDetailRepository careerDetailRepository;

    @Transactional
    public Long create(Long userId, CareerDetailCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        CareerDetail careerDetail = CareerDetail.create(request.getCompanyName(), request.getStartedAt(), request.getFinishedAt(), request.getJobContents(), user);
        return careerDetailRepository.save(careerDetail).getId();
    }

    @Transactional
    public void update(Long careerDetailId, CareerDetailUpdateRequest request) {
        CareerDetail careerDetail = careerDetailRepository.findById(careerDetailId)
                .orElseThrow(()->new NotFoundException(CAREER_DETAIL_NOT_FOUND));
        careerDetail.update(request.getCompanyName(), request.getStartedAt(), request.getFinishedAt(), request.getJobContents());
    }

    @Transactional
    public void delete(Long careerDetailId) {
        CareerDetail careerDetail = careerDetailRepository.findById(careerDetailId)
                .orElseThrow(()->new NotFoundException(CAREER_DETAIL_NOT_FOUND));
        careerDetailRepository.delete(careerDetail);
    }

    @Transactional
    public List<CareerDetailResponse> readList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        List<CareerDetailResponse> careerDetails = careerDetailRepository.findByUser(user).stream()
                .map(CareerDetailResponse::response)
                .collect(Collectors.toList());
        return careerDetails;
    }

    @Transactional
    public CareerDetailResponse read(Long careerDetailId) {
        CareerDetail careerDetail = careerDetailRepository.findById(careerDetailId)
                .orElseThrow(()->new NotFoundException(CAREER_DETAIL_NOT_FOUND));
        CareerDetailResponse careerDetailResponse = CareerDetailResponse.response(careerDetail);
        return careerDetailResponse;
    }
}
