package com.softtech.personality.personalitytest.model;

import lombok.Data;

import java.util.List;

@Data
public class Question {
    private String testId;
    private String category;
    private String question;
    private List<String> options;
    private int sequence;
    private String questionType;
    private String predicateValue;
    private int conditionalSequence;
    private String answer;

    public String internalId(){
        return this.category+"-"+this.getSequence() + "-" + this.getConditionalSequence();
    }

    public Question getItSelf(){
        return this;
    }

}
