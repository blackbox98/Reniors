package com.common.reniors.domain.repository;

import com.common.reniors.domain.entity.EvalQuestion;
import com.common.reniors.domain.entity.JobOpening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvalQuestionRepository extends JpaRepository<EvalQuestion, Long> {

    List<EvalQuestion> findByJobOpening(JobOpening jobOpening);

}
