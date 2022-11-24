package com.common.reniors.service.resume;

import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.resume.Award;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.resume.AwardRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.resume.AwardCreateRequest;
import com.common.reniors.dto.resume.AwardResponse;
import com.common.reniors.dto.resume.AwardUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotFoundException.AWARD_NOT_FOUND;
import static com.common.reniors.common.exception.NotFoundException.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AwardService {
    private final UserRepository userRepository;
    private final AwardRepository awardRepository;

    @Transactional
    public Long create(Long userId, AwardCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        Award award = Award.create(request.getName(), request.getAwardedAt(), user);
        return awardRepository.save(award).getId();
    }

    @Transactional
    public void update(Long awardId, AwardUpdateRequest request) {
        Award award = awardRepository.findById(awardId)
                .orElseThrow(()->new NotFoundException(AWARD_NOT_FOUND));
        award.update(request.getName(), request.getAwardedAt());
    }

    @Transactional
    public void delete(Long awardId) {
        Award award = awardRepository.findById(awardId)
                .orElseThrow(()->new NotFoundException(AWARD_NOT_FOUND));
        awardRepository.delete(award);
    }

    @Transactional
    public List<AwardResponse> readList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        List<AwardResponse> awards = awardRepository.findByUser(user).stream()
                .map(AwardResponse::response)
                .collect(Collectors.toList());
        return awards;
    }

    @Transactional
    public AwardResponse read(Long awardId) {
        Award award = awardRepository.findById(awardId)
                .orElseThrow(()->new NotFoundException(AWARD_NOT_FOUND));
        AwardResponse awardResponse = AwardResponse.response(award);
        return awardResponse;
    }
}