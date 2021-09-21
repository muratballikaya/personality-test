package com.mb.personality.personalitytest.model.setup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Range {
    @Positive(message = "'from' should be positive")
    public int from;
    @Positive(message = "'to' should be positive")
    public int to;
}
