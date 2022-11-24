package com.common.reniors.domain.entity.interviewQuestion;

import com.common.reniors.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    @NotNull
    @Column(length = 2000)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    public static Answer create(String a, Question question, User user){
        Answer answer = new Answer();
        answer.answer = a;

        answer.question = question;
        answer.user = user;
        return answer;
    }

    public void update(String a){
        this.answer = a;
    }
}
