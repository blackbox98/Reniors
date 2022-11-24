package com.common.reniors.domain.repository.interviewQuestion;

import com.common.reniors.domain.entity.interviewQuestion.Answer;
import com.common.reniors.domain.entity.interviewQuestion.Question;
import com.common.reniors.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByQuestionAndUser(Question question, User user);
    List<Answer> findByUser(User user);
}
