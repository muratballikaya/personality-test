package com.softtech.personality.personalitytest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    private String testId;
    private String category;
    private int sequence;
    private int conditionalSequence;
    private String questionInternalId;
    private String answer;
    private int from;
    private int to;

}
