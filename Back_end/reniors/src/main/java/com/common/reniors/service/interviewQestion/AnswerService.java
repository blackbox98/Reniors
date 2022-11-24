package com.common.reniors.service.interviewQestion;

import com.common.reniors.common.exception.NotAuthException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.interviewQuestion.Answer;
import com.common.reniors.domain.entity.interviewQuestion.Question;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.interviewQuestion.AnswerRepository;
import com.common.reniors.domain.repository.interviewQuestion.QuestionRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.category.GugunResponse;
import com.common.reniors.dto.interviewQuestion.AnswerCreateRequest;
import com.common.reniors.dto.interviewQuestion.AnswerResponse;
import com.common.reniors.dto.interviewQuestion.AnswerUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotAuthException.USER_NO_AUTH;
import static com.common.reniors.common.exception.NotFoundException.ANSWER_NOT_FOUND;
import static com.common.reniors.common.exception.NotFoundException.QUESTION_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long create(Long questionId, AnswerCreateRequest request, User user) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new NotFoundException(QUESTION_NOT_FOUND));
        if(answerRepository.findByQuestionAndUser(question, user).isPresent()){
            Answer answer = answerRepository.findByQuestionAndUser(question, user)
                    .orElseThrow(()->new NotFoundException(ANSWER_NOT_FOUND));
            answer.update(request.getAnswer());
            return answer.getId();
        }
        else{
            Answer newAnswer = Answer.create(request.getAnswer(), question, user);
            return answerRepository.save(newAnswer).getId();
        }
    }

    @Transactional
    public void update(Long questionId, AnswerUpdateRequest request, User user) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new NotFoundException(QUESTION_NOT_FOUND));
        Answer answer = answerRepository.findByQuestionAndUser(question, user)
                .orElseThrow(()->new NotFoundException(ANSWER_NOT_FOUND));
        answer.update(request.getAnswer());
    }

    @Transactional
    public void delete(Long answerId, User user) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(()->new NotFoundException(ANSWER_NOT_FOUND));
        if(answer.getUser() == user) {
            answerRepository.delete(answer);
        }else{
            throw new NotAuthException(USER_NO_AUTH);
        }
    }

    @Transactional
    public boolean answerCheck(Long questionId, User user) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new NotFoundException(QUESTION_NOT_FOUND));
        Optional answer = answerRepository.findByQuestionAndUser(question, user);

        if(answer.isPresent()) {
            return true;
        }
        else{
            return false;
        }
    }

    @Transactional
    public AnswerResponse getAnswer(Long questionId, User user) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new NotFoundException(QUESTION_NOT_FOUND));
        Answer answer = answerRepository.findByQuestionAndUser(question, user)
                .orElseThrow(()->new NotFoundException(ANSWER_NOT_FOUND));

        return AnswerResponse.response(answer);
    }

    @Transactional
    public List<AnswerResponse> getAnswerList(User user){
        List<AnswerResponse> answers = answerRepository.findByUser(user).stream()
                .filter(a -> a.getAnswer()!=null)
                .filter(a -> a.getAnswer()!="")
                .map(AnswerResponse::response)
                .collect(Collectors.toList());
        return answers;
    }
}
