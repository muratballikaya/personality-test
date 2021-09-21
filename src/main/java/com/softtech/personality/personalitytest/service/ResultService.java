package com.softtech.personality.personalitytest.service;

import com.softtech.personality.personalitytest.client.BackendClient;
import com.softtech.personality.personalitytest.model.Answer;
import com.softtech.personality.personalitytest.model.Question;
import com.softtech.personality.personalitytest.model.QuestionAnswerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    BackendClient backendClient;

    public List<QuestionAnswerModel> generateQAList(String testId) {
        List<QuestionAnswerModel> list = new ArrayList<QuestionAnswerModel>();
        List<Question> questions = backendClient.getAllQuestions(testId);
        Map<String, Question> map = generateQuestionMap(questions);
        List<Answer> answers = backendClient.getAllAnswers(testId);

        for (Answer a : answers) {
            Question q = map.get(a.getQuestionInternalId());
            QuestionAnswerModel qaModel = QuestionAnswerModel.builder().answer(a.getAnswer()).question(q.getQuestion()).category(q.getCategory()).build();
            list.add(qaModel);
        }


        return list;

    }

    private Map<String, Question> generateQuestionMap(List<Question> questions) {
        return questions.stream().collect(Collectors.toMap(Question::internalId, Question::getItSelf));
    }
}
