package com.mb.personality.personalitytest.service;

import com.mb.personality.personalitytest.client.BackendClient;
import com.mb.personality.personalitytest.model.Answer;
import com.mb.personality.personalitytest.context.SessionContext;
import com.mb.personality.personalitytest.exception.NoCategoryFoundException;
import com.mb.personality.personalitytest.exception.NoTestFoundException;
import com.mb.personality.personalitytest.exception.ThereIsNoMoreQuestionException;
import com.mb.personality.personalitytest.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalTestService {

    private static final String SUCCESS="success";

    @Autowired
    BackendClient client;

    public Question getNextQuestion(SessionContext context) {
        if(context.getTestId()==null) {
            throw  new NoTestFoundException();
        }

        //Fetch categoryList
        if(context.getCategoryList() == null) {
            List<String> categoryList = client.fetchCategoryList(context.getTestId());
            if(categoryList == null){
                throw new NoCategoryFoundException();
            }
            context.setCategoryList(categoryList);
            context.setCategory(categoryList.get(0));
            context.setCategoryIndex(0);
        }

        // get question by current category
        Question nextQuestion=null;
        if(context.getQuestion()==null){
          nextQuestion= client.nextQuestion(context.getTestId(),context.getCategory(),0,0);
        }else{
           nextQuestion = client.nextQuestion(context.getTestId(),context.getCategory(),context.getQuestion().getSequence(),context.getQuestion().getConditionalSequence());
        }

        // Question may be in the next category
        if(nextQuestion == null){
            String nextCategory =  getNextCategory(context);
            if(nextCategory != null){
                nextQuestion =client.nextQuestion(context.getTestId(),nextCategory,
                        0,0);
                context.setCategory(nextCategory);
                context.setCategoryIndex(context.getCategoryIndex()+1);
                context.setQuestion(nextQuestion);

            }else{
                throw new ThereIsNoMoreQuestionException();
            }
        }
        nextQuestion.setTestId(context.getTestId());
        context.setQuestion(nextQuestion);
        return nextQuestion;

    }

    private String getNextCategory(SessionContext context) {
        List<String> catList  =context.getCategoryList();
        Integer index = context.getCategoryIndex();
        index++;
        if(catList.size()<=index) return null;
        else{
            return catList.get(index);
        }
    }

    public boolean saveAnswer(Answer answer, SessionContext context) {
        return SUCCESS.equals(client.saveAnswer(Answer.builder().answer(answer.getAnswer())
                .testId(context.getTestId()).conditionalSequence(context.getQuestion().getConditionalSequence())
                .sequence(context.getQuestion().getSequence()).category(context.getQuestion().getCategory()).build()));
    }

}
