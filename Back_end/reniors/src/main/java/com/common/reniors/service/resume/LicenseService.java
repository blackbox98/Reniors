package com.common.reniors.service.resume;

import com.common.reniors.common.exception.DuplicateException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.resume.License;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.resume.LicenseRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.resume.LicenseCreateRequest;
import com.common.reniors.dto.resume.LicenseResponse;
import com.common.reniors.dto.resume.LicenseUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotFoundException.LICENSE_NOT_FOUND;
import static com.common.reniors.common.exception.NotFoundException.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LicenseService {
    private final UserRepository userRepository;
    private final LicenseRepository licenseRepository;

    @Transactional
    public Long create(Long userId, LicenseCreateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        licenseRepository.findByUser(user).forEach(license -> {
            if (license.getName().equals(request.getName())) {
                throw new DuplicateException(String.format("%s은/는 이미 등록된 자격증입니다.", request.getName()));
            }
        });
        License license = License.create(request.getName(), request.getPassedAt(), request.getGrade(), user);
        return licenseRepository.save(license).getId();
    }

    @Transactional
    public List<LicenseResponse> readList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new NotFoundException(USER_NOT_FOUND));
        List<LicenseResponse> licenses = licenseRepository.findByUser(user).stream()
                .map(LicenseResponse::response)
                .collect(Collectors.toList());
        return licenses;
    }

    @Transactional
    public LicenseResponse read(Long licenseId) {
        License license = licenseRepository.findById(licenseId)
                .orElseThrow(()->new NotFoundException(LICENSE_NOT_FOUND));
        LicenseResponse licenseResponse = LicenseResponse.response(license);
        return licenseResponse;
    }

    @Transactional
    public void update(Long licenseId, LicenseUpdateRequest request) {
        License license = licenseRepository.findById(licenseId)
                .orElseThrow(()->new NotFoundException(LICENSE_NOT_FOUND));
        license.update(request.getName(), request.getPassedAt(), request.getGrade());
    }

    @Transactional
    public void delete(Long licenseId) {
        License license = licenseRepository.findById(licenseId)
                .orElseThrow(()->new NotFoundException(LICENSE_NOT_FOUND));
        licenseRepository.delete(license);
    }
}
