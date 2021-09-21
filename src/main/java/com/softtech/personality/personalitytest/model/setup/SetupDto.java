package com.softtech.personality.personalitytest.model.setup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SetupDto {

    @NotEmpty(message = "category should not be empty")
    List<String> categories;
    @NotEmpty(message = "questions should not be empty")
    List<SetupQuestion> questions;

}
