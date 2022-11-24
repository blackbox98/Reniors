package com.common.reniors.service.eval;

import com.common.reniors.common.exception.NotFoundException;
import com.common.reniors.domain.entity.Company;
import com.common.reniors.domain.entity.EvalQuestion;
import com.common.reniors.domain.entity.JobOpening;
import com.common.reniors.domain.entity.UserEval;
import com.common.reniors.domain.entity.user.User;
import com.common.reniors.domain.repository.CompanyRepository;
import com.common.reniors.domain.repository.EvalQuestionRepository;
import com.common.reniors.domain.repository.JobOpeningRepository;
import com.common.reniors.domain.repository.UserEvalRepository;
import com.common.reniors.domain.repository.user.UserRepository;
import com.common.reniors.dto.eval.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EvalService {

    private final EvalQuestionRepository evalQuestionRepository;
    private final UserEvalRepository userEvalRepository;
    private final UserRepository userRepository;
    private final JobOpeningRepository jobOpeningRepository;


    //평가 질문 등록
    @Transactional
    public Long postEvalQuestion(Company company, EvalQuestionCreateRequest evalQuestionCreateRequest) {
        JobOpening jobOpening = jobOpeningRepository.findById(evalQuestionCreateRequest.getJobOpeningId()).orElseThrow(() -> new NotFoundException("not found jobOpening"));
        EvalQuestion evalQuestion = new EvalQuestion(evalQuestionCreateRequest, jobOpening);

        Long id = evalQuestionRepository.save(evalQuestion).getId();

        return id;

    }

    //평가 질문 조회
    @Transactional
    public List<EvalQuestionResponse> getEvalQuestionList(Company company, Long jobOpeningId) {
        JobOpening jobOpening = jobOpeningRepository.findById(jobOpeningId).orElseThrow(() -> new NotFoundException("not found jobOpening"));
        List<EvalQuestion> evalQuestions = evalQuestionRepository.findByJobOpening(jobOpening);

        List<EvalQuestionResponse> evalQuestionResponses = evalQuestions.stream().map(e -> EvalQuestionResponse.response(e)
        ).collect(Collectors.toList());

        return evalQuestionResponses;

    }

    //평가 질문 상세조회
    @Transactional
    public EvalQuestionResponse getEvalQuestion(Company company, Long evalQuestionId) {
        EvalQuestion evalQuestion = evalQuestionRepository.findById(evalQuestionId).orElseThrow(() -> new NotFoundException("not found evalQuestion"));

        EvalQuestionResponse evalQuestionResponse = EvalQuestionResponse.response(evalQuestion);

        return evalQuestionResponse;

    }


    //평가 질문 수정
    @Transactional
    public void updateEvalQuestion(Company company, Long evalQuestionId, EvalQuestionUpdateRequest evalQuestionUpdateRequest) {
        EvalQuestion evalQuestion = evalQuestionRepository.findById(evalQuestionId).orElseThrow(() -> new NotFoundException("not found evalQuestion"));

        evalQuestion.update(evalQuestionUpdateRequest);
    }

    //평가 질문 삭제
    @Transactional
    public void deleteEvalQuestion(Company company, Long evalQuestionId) {
        evalQuestionRepository.deleteById(evalQuestionId);
    }


    //유저 평가 입력
    @Transactional
    public Long postUserEval(Company company, UserEvalCreateRequest userEvalCreateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("not found user"));
        EvalQuestion evalquestion = evalQuestionRepository.findById(userEvalCreateRequest.getEvalQuestionId()).orElseThrow(() -> new NotFoundException("not found evalquestion"));

        UserEval userEval = new UserEval(userEvalCreateRequest, user, evalquestion);
        Long id = userEvalRepository.save(userEval).getId();

        return id;
    }

    //유저 평가 조회
    @Transactional
    public List<UserEvalResponse> getUserEvalList(Company company, Long userId, Long jobOpeningId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("not found user"));
        List<UserEval> userEvalList = userEvalRepository.findByUser(user);
        List<UserEvalResponse> userEvalResponses = new ArrayList<>();
        for (UserEval userEval : userEvalList) {
            if (userEval.getEvalQuestion().getJobOpening().getId() == jobOpeningId) {
                userEvalResponses.add(UserEvalResponse.response(userEval));
            }
        }

        return userEvalResponses;
    }

    //유저 평가 삭제
    @Transactional
    public void deleteUserEval(Company company, Long userId, Long jobOpeningId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("not found user"));
        List<UserEval> userEvalList = userEvalRepository.findByUser(user);

        for (UserEval userEval : userEvalList) {
            if (userEval.getEvalQuestion().getJobOpening().getId() == jobOpeningId) {
                userEvalRepository.deleteById(userEval.getId());
            }
        }
    }
}
