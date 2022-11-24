package com.common.reniors.domain.repository.interviewQuestion;

import com.common.reniors.domain.entity.interviewQuestion.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByQuestion(String question);
}
