package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
//    List<JobOpeningDto> findByCompanyId(Long companyId);
    Optional<Company> findByCompanyAppId (String companyAppId);
    Optional<Company> findByCompanyNum(String companyNum);
}
