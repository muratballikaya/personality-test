package com.softtech.personality.personalitytest.model.setup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Condition {
    @NotNull(message = "predicate should not be null")
    public Predicate predicate;
    @NotNull(message = "question should not be null")
    public SetupQuestion if_positive;
}
