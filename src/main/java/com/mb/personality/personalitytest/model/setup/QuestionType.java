package com.mb.personality.personalitytest.model.setup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionType {
    @NotNull(message = "type can not be null")
    String type;
    @NotEmpty(message = "options should not be empty")
    List<String> options;
    Condition condition;
    Range range;
}
