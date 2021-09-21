package com.mb.personality.personalitytest.context;

import com.mb.personality.personalitytest.model.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SessionContext {
    String testId;
    String category;
    List<String> categoryList;
    int categoryIndex;
    Question question;

    public void clear(){
        this.testId = null;
        this.category= null;
        this.categoryList = null;
        this.categoryIndex = 0;
        question = null;
    }



}
