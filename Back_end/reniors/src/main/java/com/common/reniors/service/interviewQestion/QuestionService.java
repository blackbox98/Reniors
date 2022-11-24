package com.common.reniors.service.interviewQestion;

import com.common.reniors.common.exception.DuplicateException;
import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.interviewQuestion.Question;
import com.common.reniors.domain.repository.interviewQuestion.QuestionRepository;
import com.common.reniors.dto.interviewQuestion.QuestionCreateRequest;
import com.common.reniors.dto.interviewQuestion.QuestionResponse;
import com.common.reniors.dto.interviewQuestion.QuestionUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.common.reniors.common.exception.NotFoundException.CATEGORY_NOT_FOUND;
import static com.common.reniors.common.exception.NotFoundException.QUESTION_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;


    @Transactional
    public void createList(List<QuestionCreateRequest> requestList){
        requestList.forEach(request -> {
            Question question = Question.create(request.getQuestion());
            questionRepository.save(question);
        });
    }

    @Transactional
    public Long create(QuestionCreateRequest request) {
        if(questionRepository.findByQuestion(request.getQuestion()).isPresent()){
            throw new DuplicateException("이미 존재하는 질문입니다.");
        }
        Question question = Question.create(request.getQuestion());
        return questionRepository.save(question).getId();
    }


    @Transactional
    public void update(Long questionId, QuestionUpdateRequest request) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new NotFoundException(QUESTION_NOT_FOUND));
        if(questionRepository.findByQuestion(request.getQuestion()).isPresent()){
            throw new DuplicateException("이미 존재하는 카테고리입니다.");
        }
        question.update(request.getQuestion());
    }


    @Transactional
    public void delete(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(()->new NotFoundException(CATEGORY_NOT_FOUND));
        questionRepository.delete(question);
    }


    @Transactional
    public List<QuestionResponse> getQuestionList() {
        List<QuestionResponse> questions = questionRepository.findAll().stream()
                .map(QuestionResponse::response)
                .collect(Collectors.toList());
        return questions;
    }
}
