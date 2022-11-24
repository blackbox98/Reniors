package com.common.reniors.domain.repository.category;

import com.common.reniors.domain.entity.category.Sido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SidoRepository extends JpaRepository<Sido, Long> {

    Optional<Sido> findByName(String name);
    Optional<Sido> findByCode(Long code);
}
