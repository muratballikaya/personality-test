package com.mb.personality.personalitytest.client;

import com.mb.personality.personalitytest.model.Answer;
import com.mb.personality.personalitytest.model.setup.SetupDto;
import com.mb.personality.personalitytest.model.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "backend-service", url = "${pt.backend.url}")
public interface BackendClient {

    @RequestMapping(method = RequestMethod.GET, value = "/category/{testId}")
    public List<String> fetchCategoryList(@PathVariable("testId") String testId);

    @GetMapping("/question/{testId}/{category}/{sequence}/{cSequence}")
    public Question nextQuestion(@PathVariable("testId") String testId, @PathVariable("category") String category,
                                 @PathVariable("sequence") int sequence,
                                 @PathVariable("cSequence") int csequence);

    @PutMapping("/answer")
    public String saveAnswer(@RequestBody Answer answer);

    @PostMapping("/setup")
    public String saveTest(@RequestBody SetupDto setupDto);

    @GetMapping("/answer/{testId}")
    public List<Answer> getAllAnswers(@PathVariable("testId") String testId);

    @GetMapping("/question/{testId}")
    public List<Question> getAllQuestions(@PathVariable("testId") String testId);

}