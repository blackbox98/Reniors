package com.common.reniors.domain.repository.category;

import com.common.reniors.domain.entity.category.Gugun;
import com.common.reniors.domain.entity.category.Sido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GugunRepository extends JpaRepository<Gugun, Long> {
    Optional<Gugun> findByName(String name);
    List<Gugun> findBySido(Sido sido);

}
